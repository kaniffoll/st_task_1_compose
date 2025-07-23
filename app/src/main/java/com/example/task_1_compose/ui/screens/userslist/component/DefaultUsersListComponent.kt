package com.example.task_1_compose.ui.screens.userslist.component

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.task_1_compose.ui.screens.userslist.store.UsersListStoreFactory

class DefaultUsersListComponent(
    componentContext: ComponentContext,
    appContext: Context
) : UsersListComponent, ComponentContext by componentContext {
    override val store = instanceKeeper.getStore {
        UsersListStoreFactory(
            DefaultStoreFactory(),
            appContext
        ).create()
    }
}