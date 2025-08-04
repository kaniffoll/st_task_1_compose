package com.example.task_1_compose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.example.domain.resources.ComponentKeyNames.ALBUMS_LIST_COMPONENT_KEY_NAME
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
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class MainComponent(
    componentContext: ComponentContext,
    koin: Koin = GlobalContext.get()
) : ComponentContext by componentContext {

    private val todosScope = koin.createScope(
        scopeId = TODOS_COMPONENT_KEY_NAME,
        qualifier = named<DefaultTodosComponent>()
    )

    private val usersListScope = koin.createScope(
        scopeId = USERS_LIST_COMPONENT_KEY_NAME,
        qualifier = named<DefaultUsersListComponent>()
    )

    private val postsListScope = koin.createScope(
        scopeId = POSTS_LIST_COMPONENT_KEY_NAME,
        qualifier = named<DefaultPostsListComponent>()
    )

    private val userScreenScope = koin.createScope(
        scopeId = USER_SCREEN_COMPONENT_KEY_NAME,
        qualifier = named<DefaultUserScreenComponent>()
    )

    private val albumsListScope = koin.createScope(
        scopeId = ALBUMS_LIST_COMPONENT_KEY_NAME,
        qualifier = named<DefaultAlbumsListComponent>()
    )

    private val postScreenScope = koin.createScope(
        scopeId = POST_SCREEN_COMPONENT_KEY_NAME,
        qualifier = named<DefaultPostScreenComponent>()
    )

    private val albumScreenScope = koin.createScope(
        scopeId = ALBUM_SCREEN_COMPONENT_KEY_NAME,
        qualifier = named<DefaultAlbumScreenComponent>()
    )

    init {
        componentContext.doOnDestroy {
            todosScope.close()
            usersListScope.close()
            postsListScope.close()
            userScreenScope.close()
            albumsListScope.close()
            postScreenScope.close()
            albumScreenScope.close()
        }
    }

    val todosComponent: DefaultTodosComponent =
        todosScope.get { parametersOf(childContext(key = TODOS_COMPONENT_KEY_NAME)) }

    val usersListComponent: DefaultUsersListComponent =
        usersListScope.get { parametersOf(childContext(key = USERS_LIST_COMPONENT_KEY_NAME)) }

    val postsListComponent: DefaultPostsListComponent =
        postsListScope.get { parametersOf(childContext(key = POSTS_LIST_COMPONENT_KEY_NAME)) }

    val userScreenComponent: DefaultUserScreenComponent =
        userScreenScope.get { parametersOf(childContext(key = USER_SCREEN_COMPONENT_KEY_NAME)) }

    val albumsListComponent: DefaultAlbumsListComponent =
        albumsListScope.get { parametersOf(childContext(key = ALBUMS_LIST_COMPONENT_KEY_NAME)) }

    val postScreenComponent: DefaultPostScreenComponent =
        postScreenScope.get { parametersOf(childContext(key = POST_SCREEN_COMPONENT_KEY_NAME)) }

    val albumScreenComponent: DefaultAlbumScreenComponent =
        albumScreenScope.get { parametersOf(childContext(key = ALBUM_SCREEN_COMPONENT_KEY_NAME)) }
}