package com.example.task_1_compose.ui.screens.post.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.data.Post
import com.example.domain.repositories.PostsRepository
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.StringResources.POST_SCREEN_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

internal class PostScreenStoreFactory(
    private val storeFactory: StoreFactory,
    context: Context,
    private val post: Post
) {
    private val resourceProvider = ResourceProvider(context)
    private val repository = PostsRepository()

    fun create(): PostScreenStore = object : PostScreenStore,
        Store<PostScreenIntent, PostScreenState, Nothing> by storeFactory.create(
            name = POST_SCREEN_STORE_NAME,
            initialState = PostScreenState(post),
            bootstrapper = SimpleBootstrapper(PostScreenAction.LoadInitialComments),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<PostScreenIntent, PostScreenAction, PostScreenState, PostScreenMsg, Nothing>() {
        override fun executeIntent(intent: PostScreenIntent) {
            when (intent) {
                is PostScreenIntent.LoadNextComments -> {
                    loadNextComments()
                }

                is PostScreenIntent.ToggleLike -> {
                    val updatedPost = state().post.copy(isLiked = !state().post.isLiked)
                    dispatch(PostScreenMsg.LikeClicked(updatedPost))
                }
            }


        }

        override fun executeAction(action: PostScreenAction) {
            when (action) {
                is PostScreenAction.LoadInitialComments -> {
                    loadNextComments()
                }
            }
        }

        private fun loadNextComments() {
            scope.launch {
                if (!canLoadMoreComments()) {
                    dispatch(PostScreenMsg.AllCommentsLoaded)
                    return@launch
                }

                when (
                    val newComments = repository
                        .loadPostCommentsById(state().post.id, state().currentPage)
                ) {
                    null -> {
                        dispatch(
                            PostScreenMsg.CommentsLoadError(
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
                            PostScreenMsg.NextCommentsLoaded(
                                state().post.copy(
                                    comments = (state().post.comments + newComments).toMutableList()
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

    private object ReducerImpl : Reducer<PostScreenState, PostScreenMsg> {
        override fun PostScreenState.reduce(msg: PostScreenMsg): PostScreenState = when (msg) {
            is PostScreenMsg.AllCommentsLoaded -> this
            is PostScreenMsg.CommentsLoadError -> copy(statefulData = msg.statefulData)
            is PostScreenMsg.LikeClicked -> copy(post = msg.post)
            is PostScreenMsg.NextCommentsLoaded -> copy(
                post = msg.post,
                statefulData = SuccessData(result = msg.post.comments),
                currentPage = currentPage + 1
            )
        }
    }
}