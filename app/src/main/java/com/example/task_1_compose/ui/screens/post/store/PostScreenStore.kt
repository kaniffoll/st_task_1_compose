package com.example.task_1_compose.ui.screens.post.store

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

interface PostScreenStore : Store<PostScreenIntent, PostScreenState, Nothing>

sealed interface PostScreenIntent {
    data object ToggleLike : PostScreenIntent
    data object LoadNextComments : PostScreenIntent
    data class InitializePostScreen(val post: Post) : PostScreenIntent
}

sealed interface PostScreenMsg {
    data class LikeClicked(val post: Post) : PostScreenMsg
    data class NextCommentsLoaded(val post: Post) : PostScreenMsg
    data object AllCommentsLoaded : PostScreenMsg
    data class CommentsLoadError(val statefulData: StatefulData<List<Comment>>) : PostScreenMsg
    data class PostInitialized(val post: Post) : PostScreenMsg
}

data class PostScreenState(
    val post: Post?,
    val statefulData: StatefulData<List<Comment>> = LoadingData(),
    val currentPage: Int = 0
)

interface PostScreenComponent {
    val store: PostScreenStore
}

class DefaultPostScreenComponent(val componentContext: ComponentContext, val appContext: Context) :
    PostScreenComponent,
    ComponentContext by componentContext {
    override val store = instanceKeeper.getStore {
        PostScreenStoreFactory(
            DefaultStoreFactory(),
            appContext,
            null
        ).create()
    }

}