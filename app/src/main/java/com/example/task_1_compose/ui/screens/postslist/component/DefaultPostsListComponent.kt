package com.example.task_1_compose.ui.screens.postslist.component

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.task_1_compose.ui.screens.postslist.store.PostsListStoreFactory

class DefaultPostsListComponent(
    componentContext: ComponentContext,
    appContext: Context
) : PostsListComponent, ComponentContext by componentContext {
    override val store =
        instanceKeeper.getStore { PostsListStoreFactory(DefaultStoreFactory(), appContext).create() }
}