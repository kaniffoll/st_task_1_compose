package com.example.task_1_compose

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.example.domain.resources.StringResources.POSTS_LIST_COMPONENT_KEY_NAME
import com.example.domain.resources.StringResources.TODOS_COMPONENT_KEY_NAME
import com.example.domain.resources.StringResources.USERS_LIST_COMPONENT_KEY_NAME
import com.example.task_1_compose.ui.screens.postslist.component.DefaultPostsListComponent
import com.example.task_1_compose.ui.screens.todoslist.component.DefaultTodosComponent
import com.example.task_1_compose.ui.screens.userslist.component.DefaultUsersListComponent

class MainComponent(
    componentContext: ComponentContext,
    appContext: Context,
) : ComponentContext by componentContext {

    val todosComponent = DefaultTodosComponent(childContext(key = TODOS_COMPONENT_KEY_NAME))

    val usersListComponent =
        DefaultUsersListComponent(childContext(key = USERS_LIST_COMPONENT_KEY_NAME), appContext)

    val postsListComponent =
        DefaultPostsListComponent(childContext(key = POSTS_LIST_COMPONENT_KEY_NAME), appContext)
}