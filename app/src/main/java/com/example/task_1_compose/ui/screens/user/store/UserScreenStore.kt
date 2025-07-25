package com.example.task_1_compose.ui.screens.user.store

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

interface UserScreenStore : Store<UserScreenIntent, UserScreenState, Nothing>

sealed interface UserScreenIntent {
    data object LoadComments : UserScreenIntent
    data class InitializeUserScreen(val user: User) : UserScreenIntent
}

sealed interface UserScreenMsg {
    data class NextCommentsLoaded(val user: User) : UserScreenMsg
    data object AllCommentsLoaded : UserScreenMsg
    data class CommentsLoadError(val statefulData: StatefulData<List<Comment>>) : UserScreenMsg
    data class UserInitialized(val user: User) : UserScreenMsg
}

data class UserScreenState(
    val comments: StatefulData<List<Comment>> = LoadingData(),
    val currentUser: User?,
    val currentPage: Int = 0
)

interface UserScreenComponent {
    val store: UserScreenStore
}

class DefaultUserScreenComponent(componentContext: ComponentContext, appContext: Context) :
    UserScreenComponent, ComponentContext by componentContext {
    override val store: UserScreenStore = instanceKeeper.getStore {  UserScreenStoreFactory(DefaultStoreFactory(), null, appContext).create() }
}
