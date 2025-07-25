package com.example.task_1_compose.ui.screens.userslist.store

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.data.User
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

interface UsersListStore : Store<UsersListIntent, UsersListState, Nothing>

sealed interface UsersListIntent {
    data object LoadUsers : UsersListIntent
}

sealed interface UsersListMsg {
    data class UsersLoaded(val users: List<User>) : UsersListMsg
    data class UsersLoadError(val errorDetails: ErrorData<List<User>>) : UsersListMsg
}

data class UsersListState(
    val users: StatefulData<List<User>> = LoadingData()
)

interface UsersListComponent {
    val store: UsersListStore
}

class DefaultUsersListComponent(
    componentContext: ComponentContext,
) : UsersListComponent, ComponentContext by componentContext {
    override val store = instanceKeeper.getStore {
        UsersListStoreFactory(
            DefaultStoreFactory()
        ).create()
    }
}