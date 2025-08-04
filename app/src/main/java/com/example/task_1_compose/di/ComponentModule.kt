package com.example.task_1_compose.di

import com.arkivanov.decompose.ComponentContext
import com.example.task_1_compose.ui.screens.album.store.DefaultAlbumScreenComponent
import com.example.task_1_compose.ui.screens.albumslist.store.DefaultAlbumsListComponent
import com.example.task_1_compose.ui.screens.post.store.DefaultPostScreenComponent
import com.example.task_1_compose.ui.screens.postslist.store.DefaultPostsListComponent
import com.example.task_1_compose.ui.screens.todoslist.store.DefaultTodosComponent
import com.example.task_1_compose.ui.screens.user.store.DefaultUserScreenComponent
import com.example.task_1_compose.ui.screens.userslist.store.DefaultUsersListComponent
import org.koin.dsl.module

val componentModule = module {
    scope<DefaultTodosComponent> {
        scoped { (ctx: ComponentContext) ->
            DefaultTodosComponent(ctx, get())
        }
    }

    scope<DefaultUsersListComponent> {
        scoped { (ctx: ComponentContext) ->
            DefaultUsersListComponent(ctx, get())
        }
    }

    scope<DefaultPostsListComponent> {
        scoped { (ctx: ComponentContext) ->
            DefaultPostsListComponent(ctx, get())
        }
    }

    scope<DefaultUserScreenComponent> {
        scoped { (ctx: ComponentContext) ->
            DefaultUserScreenComponent(ctx, get())
        }
    }

    scope<DefaultAlbumsListComponent> {
        scoped { (ctx: ComponentContext) ->
            DefaultAlbumsListComponent(ctx, get())
        }
    }

    scope<DefaultPostScreenComponent> {
        scoped { (ctx: ComponentContext) ->
            DefaultPostScreenComponent(ctx, get())
        }
    }

    scope<DefaultAlbumScreenComponent> {
        scoped { (ctx: ComponentContext) ->
            DefaultAlbumScreenComponent(ctx, get())
        }
    }
}