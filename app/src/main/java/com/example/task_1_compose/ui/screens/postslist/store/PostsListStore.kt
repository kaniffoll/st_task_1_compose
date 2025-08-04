package com.example.task_1_compose.ui.screens.postslist.store

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.data.Post
import com.example.domain.repositories.PostsRepository
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.StatefulData

interface PostsListStore : Store<PostsListIntent, PostsListState, Nothing>

sealed interface PostsListIntent {
    data class ToggleLike(val id: Int) : PostsListIntent
    data object LoadNextPosts : PostsListIntent
}

sealed interface PostsListMsg {
    class LikeClicked(val posts: List<Post>) : PostsListMsg
    data object LikeFailed : PostsListMsg
    data object AllPostsLoaded : PostsListMsg
    class NextPostsLoaded(val posts: List<Post>) : PostsListMsg
    class PostsLoadError(val errorDetails: ErrorData<List<Post>>) : PostsListMsg
}

data class PostsListState(
    val posts: StatefulData<List<Post>>,
    val currentPage: Int = 1
)

interface PostsListComponent {
    val store: PostsListStore
}

class DefaultPostsListComponent(
    componentContext: ComponentContext,
    repository: PostsRepository
) : PostsListComponent, ComponentContext by componentContext {
    override val store =
        instanceKeeper.getStore {
            PostsListStoreFactory(
                repository,
                DefaultStoreFactory()
            ).create()
        }
}