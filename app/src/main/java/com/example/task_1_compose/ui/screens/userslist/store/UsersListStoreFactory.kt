package com.example.task_1_compose.ui.screens.userslist.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.repositories.UsersRepository
import com.example.domain.resources.StringResources.USERS_LIST_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

internal class UsersListStoreFactory(
    private val storeFactory: StoreFactory,
    context: Context
) {
    private val resourceProvider = ResourceProvider(context)
    private val repository = UsersRepository()

    fun create(): UsersListStore = object : UsersListStore,
        Store<UsersListIntent, UsersListState, Nothing> by storeFactory.create(
            name = USERS_LIST_STORE_NAME,
            initialState = UsersListState(),
            bootstrapper = SimpleBootstrapper(UsersListAction.LoadUsers),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<UsersListIntent, UsersListAction, UsersListState, UsersListMsg, Nothing>() {
        override fun executeIntent(intent: UsersListIntent) {
            when (intent) {
                is UsersListIntent.LoadUsers -> {
                    loadUsers()
                }
            }
        }

        override fun executeAction(action: UsersListAction) {
            when (action) {
                is UsersListAction.LoadUsers -> {
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
                                    resourceProvider.getStringResource(
                                        R.string.loading_users_error
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
            is UsersListMsg.UsersLoadError -> copy(statefulData = msg.statefulData)
            is UsersListMsg.UsersLoaded -> copy(
                statefulData = SuccessData(msg.users),
                currentUsers = msg.users
            )
        }
    }
}