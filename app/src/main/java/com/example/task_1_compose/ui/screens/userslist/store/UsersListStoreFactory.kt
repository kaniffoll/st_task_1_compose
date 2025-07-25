package com.example.task_1_compose.ui.screens.userslist.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.repositories.UsersRepository
import com.example.domain.resources.MviStoreNames.USERS_LIST_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

internal class UsersListStoreFactory(
    private val storeFactory: StoreFactory,
    private val context: Context
) {
    private val repository = UsersRepository()

    fun create(): UsersListStore = object : UsersListStore,
        Store<UsersListIntent, UsersListState, Nothing> by storeFactory.create(
            name = USERS_LIST_STORE_NAME,
            initialState = UsersListState(),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<UsersListIntent, Nothing, UsersListState, UsersListMsg, Nothing>() {
        override fun executeIntent(intent: UsersListIntent) {
            when (intent) {
                is UsersListIntent.LoadUsers -> {
                    loadUsers()
                }
            }
        }

        private fun loadUsers() {
            scope.launch {
                when (val loadedUsers = repository.loadUsers()) {
                    null -> {
                        dispatch(
                            UsersListMsg.UsersLoadError(
                                ErrorData(
                                    ResourceProvider.getStringResource(
                                        R.string.loading_users_error,
                                        context = context,
                                    )
                                )
                            )
                        )
                    }

                    else -> {
                        dispatch(
                            UsersListMsg.UsersLoaded(
                                loadedUsers
                            )
                        )
                    }
                }
            }
        }
    }

    private object ReducerImpl : Reducer<UsersListState, UsersListMsg> {
        override fun UsersListState.reduce(msg: UsersListMsg): UsersListState = when (msg) {
            is UsersListMsg.UsersLoadError -> copy(users = msg.statefulData)
            is UsersListMsg.UsersLoaded -> copy(
                users = SuccessData(msg.users)
            )
        }
    }
}