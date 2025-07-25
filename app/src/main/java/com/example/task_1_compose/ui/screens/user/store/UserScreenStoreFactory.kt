package com.example.task_1_compose.ui.screens.user.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.data.User
import com.example.domain.repositories.UsersRepository
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.MviStoreNames.USER_SCREEN_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

internal class UserScreenStoreFactory(
    private val storeFactory: StoreFactory,
    private val user: User?,
    private val context: Context
) {
    private val repository = UsersRepository()

    fun create(): UserScreenStore = object : UserScreenStore,
        Store<UserScreenIntent, UserScreenState, Nothing> by storeFactory.create(
            name = USER_SCREEN_STORE_NAME,
            initialState = UserScreenState(currentUser = user),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<UserScreenIntent, Nothing, UserScreenState, UserScreenMsg, Nothing>() {
        override fun executeIntent(intent: UserScreenIntent) {
            when (intent) {
                is UserScreenIntent.LoadComments -> {
                    loadNextComments()
                }

                is UserScreenIntent.InitializeUserScreen -> {
                    dispatch(UserScreenMsg.UserInitialized(intent.user))
                }
            }
        }

        private fun loadNextComments() {
            scope.launch {
                if (!canLoadMoreComments()) {
                    dispatch(UserScreenMsg.AllCommentsLoaded)
                    return@launch
                }

                val currentUser = state().currentUser ?: run {
                    dispatch(
                        UserScreenMsg.CommentsLoadError(
                            ErrorData(
                                ResourceProvider.getStringResource(
                                    R.string.loading_comments_error,
                                    context
                                )
                            )
                        )
                    )
                    return@launch
                }

                when (val newComments =
                    repository.loadUserCommentsById(currentUser.id, state().currentPage)) {
                    null -> {
                        dispatch(
                            UserScreenMsg.CommentsLoadError(
                                ErrorData(
                                    ResourceProvider.getStringResource(
                                        R.string.loading_comments_error,
                                        context
                                    )
                                )
                            )
                        )
                    }

                    else -> {
                        dispatch(
                            UserScreenMsg.NextCommentsLoaded(
                                currentUser.copy(
                                    comments = (currentUser.comments + newComments).toMutableList()
                                )
                            )
                        )
                    }
                }
            }
        }

        private fun canLoadMoreComments(): Boolean {
            return state().comments.canLoadMore(COMMENTS_PER_PAGE)
        }
    }

    private object ReducerImpl : Reducer<UserScreenState, UserScreenMsg> {
        override fun UserScreenState.reduce(msg: UserScreenMsg): UserScreenState = when (msg) {
            is UserScreenMsg.AllCommentsLoaded -> this
            is UserScreenMsg.CommentsLoadError -> copy(comments = msg.statefulData)
            is UserScreenMsg.NextCommentsLoaded -> copy(
                comments = SuccessData(msg.user.comments),
                currentUser = msg.user,
                currentPage = currentPage + 1
            )
            is UserScreenMsg.UserInitialized -> copy(
                comments = LoadingData(),
                currentUser = msg.user,
                currentPage = 0
            )
        }
    }
}