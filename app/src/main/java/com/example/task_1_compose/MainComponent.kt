package com.example.task_1_compose

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.example.domain.resources.ComponentKeyNames.ALBUMS_SCREEN_COMPONENT_KEY_NAME
import com.example.domain.resources.ComponentKeyNames.ALBUM_SCREEN_COMPONENT_KEY_NAME
import com.example.domain.resources.ComponentKeyNames.POSTS_LIST_COMPONENT_KEY_NAME
import com.example.domain.resources.ComponentKeyNames.POST_SCREEN_COMPONENT_KEY_NAME
import com.example.domain.resources.ComponentKeyNames.TODOS_COMPONENT_KEY_NAME
import com.example.domain.resources.ComponentKeyNames.USERS_LIST_COMPONENT_KEY_NAME
import com.example.domain.resources.ComponentKeyNames.USER_SCREEN_COMPONENT_KEY_NAME
import com.example.task_1_compose.ui.screens.album.store.DefaultAlbumScreenComponent
import com.example.task_1_compose.ui.screens.albumslist.store.DefaultAlbumsListComponent
import com.example.task_1_compose.ui.screens.post.store.DefaultPostScreenComponent
import com.example.task_1_compose.ui.screens.postslist.store.DefaultPostsListComponent
import com.example.task_1_compose.ui.screens.todoslist.store.DefaultTodosComponent
import com.example.task_1_compose.ui.screens.user.store.DefaultUserScreenComponent
import com.example.task_1_compose.ui.screens.userslist.store.DefaultUsersListComponent

class MainComponent(
    componentContext: ComponentContext,
    appContext: Context,
) : ComponentContext by componentContext {

    val todosComponent = DefaultTodosComponent(childContext(key = TODOS_COMPONENT_KEY_NAME))

    val usersListComponent =
        DefaultUsersListComponent(childContext(key = USERS_LIST_COMPONENT_KEY_NAME), appContext)

    val postsListComponent =
        DefaultPostsListComponent(childContext(key = POSTS_LIST_COMPONENT_KEY_NAME), appContext)

    val userScreenComponent =
        DefaultUserScreenComponent(childContext(key = USER_SCREEN_COMPONENT_KEY_NAME), appContext)

    val albumsListComponent =
        DefaultAlbumsListComponent(childContext(key = ALBUMS_SCREEN_COMPONENT_KEY_NAME), appContext)

    val postScreenComponent =
        DefaultPostScreenComponent(childContext(key = POST_SCREEN_COMPONENT_KEY_NAME), appContext)

    val albumScreenComponent =
        DefaultAlbumScreenComponent(childContext(key = ALBUM_SCREEN_COMPONENT_KEY_NAME), appContext)
}