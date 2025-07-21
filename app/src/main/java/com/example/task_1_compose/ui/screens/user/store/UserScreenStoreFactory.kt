package com.example.task_1_compose.ui.screens.user.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.data.User
import com.example.domain.repositories.UsersRepository
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.StringResources.USER_SCREEN_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

internal class UserScreenStoreFactory(
    private val storeFactory: StoreFactory, private val user: User, context: Context
) {
    private val resourceProvider = ResourceProvider(context)
    private val repository = UsersRepository()

    fun create(): UserScreenStore = object : UserScreenStore,
        Store<UserScreenIntent, UserScreenState, Nothing> by storeFactory.create(
            name = USER_SCREEN_STORE_NAME,
            initialState = UserScreenState(currentUser = user),
            bootstrapper = SimpleBootstrapper(UserScreenAction.LoadInitialComments),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<UserScreenIntent, UserScreenAction, UserScreenState, UserScreenMsg, Nothing>() {
        override fun executeIntent(intent: UserScreenIntent) {
            when (intent) {
                is UserScreenIntent.LoadComments -> {
                    loadNextComments()
                }
            }
        }

        override fun executeAction(action: UserScreenAction) {
            when (action) {
                is UserScreenAction.LoadInitialComments -> {
                    loadNextComments()
                }
            }
        }

        private fun loadNextComments() {
            scope.launch {
                if (!canLoadMoreComments()) {
                    dispatch(UserScreenMsg.AllCommentsLoaded)
                    return@launch
                }

                when (val newComments =
                    repository.loadUserCommentsById(state().currentUser.id, state().currentPage)) {
                    null -> {
                        dispatch(
                            UserScreenMsg.CommentsLoadError(
                                ErrorData(
                                    resourceProvider.getStringResource(
                                        R.string.loading_comments_error
                                    )
                                )
                            )
                        )
                    }

                    else -> {
                        dispatch(
                            UserScreenMsg.NextCommentsLoaded(
                                state().currentUser.copy(
                                    comments = (state().currentUser.comments + newComments).toMutableList()
                                )
                            )
                        )
                    }
                }
            }
        }

        private fun canLoadMoreComments(): Boolean {
            return state().statefulData.canLoadMore(COMMENTS_PER_PAGE)
        }
    }

    private object ReducerImpl : Reducer<UserScreenState, UserScreenMsg> {
        override fun UserScreenState.reduce(msg: UserScreenMsg): UserScreenState = when (msg) {
            is UserScreenMsg.AllCommentsLoaded -> this
            is UserScreenMsg.CommentsLoadError -> copy(statefulData = msg.statefulData)
            is UserScreenMsg.NextCommentsLoaded -> copy(
                statefulData = SuccessData(msg.user.comments),
                currentUser = msg.user,
                currentPage = currentPage + 1
            )
        }
    }
}