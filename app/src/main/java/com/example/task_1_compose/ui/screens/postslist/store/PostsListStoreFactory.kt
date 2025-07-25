package com.example.task_1_compose.ui.screens.postslist.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.data.Post
import com.example.domain.repositories.PostsRepository
import com.example.domain.resources.AppSettings
import com.example.domain.resources.MviStoreNames.POSTS_LIST_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

internal class PostsListStoreFactory(
    private val storeFactory: StoreFactory,
    private val context: Context
) {
    private val repository = PostsRepository()

    fun create(): PostsListStore = object : PostsListStore,
        Store<PostsListIntent, PostsListState, Nothing> by storeFactory.create(
            name = POSTS_LIST_STORE_NAME,
            initialState = PostsListState(LoadingData()),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}


    private inner class ExecutorImpl :
        CoroutineExecutor<PostsListIntent, Nothing, PostsListState, PostsListMsg, Nothing>() {
        override fun executeIntent(intent: PostsListIntent) {
            when (intent) {
                is PostsListIntent.ToggleLike -> {
                    if (state().posts !is SuccessData<List<Post>>) {
                        dispatch(PostsListMsg.LikeFailed)
                    } else {
                        val currentState = state().posts as SuccessData

                        dispatch(
                            PostsListMsg.LikeClicked(
                                posts = currentState.result.map {
                                    if (it.id == intent.id) {
                                        it.copy(isLiked = !it.isLiked)
                                    } else {
                                        it
                                    }
                                }
                            )
                        )
                    }
                }

                is PostsListIntent.LoadNextPosts -> {
                    scope.launch {
                        if (!canLoadMorePosts()) {
                            dispatch(PostsListMsg.AllPostsLoaded)
                            return@launch
                        }

                        when (val newPosts = repository.loadNextPosts(state().currentPage)) {
                            null -> {
                                dispatch(
                                    PostsListMsg.PostsLoadError(
                                        ErrorData(
                                            ResourceProvider.getStringResource(
                                                R.string.loading_posts_error,
                                                context
                                            )
                                        )
                                    )
                                )
                            }

                            else -> {
                                dispatch(
                                    PostsListMsg.NextPostsLoaded(
                                        newPosts
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        private fun canLoadMorePosts(): Boolean {
            return state().posts.canLoadMore(AppSettings.POSTS_PER_PAGE)
        }
    }

    private object ReducerImpl : Reducer<PostsListState, PostsListMsg> {
        override fun PostsListState.reduce(msg: PostsListMsg): PostsListState = when (msg) {
            is PostsListMsg.LikeClicked -> copy(
                posts = SuccessData(msg.posts)
            )

            is PostsListMsg.LikeFailed -> this
            is PostsListMsg.PostsLoadError -> copy(posts = msg.statefulData)
            is PostsListMsg.AllPostsLoaded -> this
            is PostsListMsg.NextPostsLoaded -> copy(
                posts = SuccessData(msg.posts),
                currentPage = currentPage + 1
            )
        }
    }
}