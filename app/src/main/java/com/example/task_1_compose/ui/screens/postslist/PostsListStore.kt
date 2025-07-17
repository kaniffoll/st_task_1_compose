package com.example.task_1_compose.ui.screens.postslist

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.data.Post
import com.example.domain.repositories.PostsRepository
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

internal interface PostsListStore : Store<PostsListIntent, PostsListState, Nothing>

sealed interface PostsListIntent {
    data class ToggleLike(val id: Int) : PostsListIntent
    data object LoadNextPosts : PostsListIntent
}

data class PostsListState(
    val statefulData: StatefulData<List<Post>>,
    val currentPage: Int = 1,
    val currentPosts: List<Post> = emptyList()
)

internal class PostsListStoreFactory(
    private val storeFactory: StoreFactory,
    context: Context
) {
    private val resourceProvider = ResourceProvider(context)
    private val repository = PostsRepository()

    fun create(): PostsListStore = object : PostsListStore,
        Store<PostsListIntent, PostsListState, Nothing> by storeFactory.create(
            name = "PostsList",
            initialState = PostsListState(LoadingData()),
            reducer = ReducerImpl,
            executorFactory = ::ExecutorImpl
        ) {}


    sealed interface Msg {
        class LikeClicked(val posts: List<Post>) : Msg
        data object LikeFailed : Msg
        data object AllPostsLoaded : Msg
        class NextPostsLoaded(val posts: List<Post>) : Msg
        class PostsLoadError(val statefulData: StatefulData<List<Post>>) : Msg
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<PostsListIntent, Nothing, PostsListState, Msg, Nothing>() {
        override fun executeIntent(intent: PostsListIntent) {
            when (intent) {
                is PostsListIntent.ToggleLike -> {
                    if (state().statefulData !is SuccessData<List<Post>>) {
                        dispatch(Msg.LikeFailed)
                    } else {
                        val currentState = state().statefulData as SuccessData

                        dispatch(
                            Msg.LikeClicked(
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
                            dispatch(Msg.AllPostsLoaded)
                            return@launch
                        }

                        //TODO: по какой-то причине при первой загрузке LoadNextPosts вызывается дважды
                        //это не ломает загрузку, но так не должно работать

                        when (val newPosts = repository.loadNextPosts(state().currentPage)) {
                            null -> {
                                dispatch(
                                    Msg.PostsLoadError(
                                        ErrorData(
                                            resourceProvider.getStringResource(
                                                R.string.loading_posts_error
                                            )
                                        )
                                    )
                                )
                            }

                            else -> {
                                dispatch(
                                    Msg.NextPostsLoaded(
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
            return state().statefulData.canLoadMore(POSTS_PER_PAGE)
        }
    }

    private object ReducerImpl : Reducer<PostsListState, Msg> {
        override fun PostsListState.reduce(msg: Msg): PostsListState = when (msg) {
            is Msg.LikeClicked -> copy(
                statefulData = SuccessData(msg.posts),
                currentPosts = msg.posts
            )
            is Msg.LikeFailed -> this
            is Msg.PostsLoadError -> copy(statefulData = msg.statefulData)
            is Msg.AllPostsLoaded -> this
            is Msg.NextPostsLoaded -> copy(
                statefulData = SuccessData(msg.posts),
                currentPage = currentPage + 1,
                currentPosts = msg.posts
            )
        }
    }
}