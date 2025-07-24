package com.example.task_1_compose.ui.screens.post.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.data.Post
import com.example.domain.repositories.PostsRepository
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.MviStoreNames.POST_SCREEN_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import com.example.task_1_compose.ui.screens.post.store.PostScreenMsg.AllCommentsLoaded
import com.example.task_1_compose.ui.screens.post.store.PostScreenMsg.CommentsLoadError
import com.example.task_1_compose.ui.screens.post.store.PostScreenMsg.LikeClicked
import com.example.task_1_compose.ui.screens.post.store.PostScreenMsg.NextCommentsLoaded
import com.example.task_1_compose.ui.screens.post.store.PostScreenMsg.PostInitialized
import kotlinx.coroutines.launch

internal class PostScreenStoreFactory(
    private val storeFactory: StoreFactory,
    private val context: Context,
    private val post: Post?
) {
    private val repository = PostsRepository()

    fun create(): PostScreenStore = object : PostScreenStore,
        Store<PostScreenIntent, PostScreenState, Nothing> by storeFactory.create(
            name = POST_SCREEN_STORE_NAME,
            initialState = PostScreenState(post),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<PostScreenIntent, Nothing, PostScreenState, PostScreenMsg, Nothing>() {
        override fun executeIntent(intent: PostScreenIntent) {
            when (intent) {
                is PostScreenIntent.LoadNextComments -> {
                    loadNextComments()
                }

                is PostScreenIntent.ToggleLike -> {
                    val updatedPost = state().post!!.copy(isLiked = !state().post!!.isLiked)
                    dispatch(LikeClicked(updatedPost))
                }

                is PostScreenIntent.InitializePostScreen -> {
                    dispatch(PostInitialized(intent.post))
                }
            }
        }

        private fun loadNextComments() {
            scope.launch {
                if (!canLoadMoreComments()) {
                    dispatch(AllCommentsLoaded)
                    return@launch
                }

                val currentPost = state().post ?: run {
                    dispatch(
                        CommentsLoadError(
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

                when (
                    val newComments = repository
                        .loadPostCommentsById(currentPost.id, state().currentPage)
                ) {
                    null -> {
                        dispatch(
                            CommentsLoadError(
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
                            NextCommentsLoaded(
                                currentPost.copy(
                                    comments = (currentPost.comments + newComments).toMutableList()
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
            is AllCommentsLoaded -> this
            is CommentsLoadError -> copy(statefulData = msg.statefulData)
            is LikeClicked -> copy(post = msg.post)
            is NextCommentsLoaded -> copy(
                post = msg.post,
                statefulData = SuccessData(result = msg.post.comments),
                currentPage = currentPage + 1
            )

            is PostInitialized -> copy(
                post = msg.post,
                statefulData = LoadingData(),
                currentPage = 0
            )
        }
    }
}