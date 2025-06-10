package com.example.task_1_compose.navigation

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.task_1_compose.R

data class NavBarItem(
    @StringRes val title: Int,
    @ColorRes val color: Int,
    val appRoute: AppRoute
)

val navBarItems = listOf(
    NavBarItem(
        title = R.string.posts,
        color = R.color.blue_for_posts,
        appRoute = PostListRoute
    ),
    NavBarItem(
        title = R.string.photos,
        color = R.color.orange_for_photos,
        appRoute = AlbumsListRoute
    ),
    NavBarItem(
        title = R.string.todos,
        color = R.color.red_for_todos,
        appRoute = TodosListRoute
    ),
    NavBarItem(
        title = R.string.users,
        color = R.color.cyan_for_users,
        appRoute = UsersListRoute
    )
)

val EmptyTopBars = listOf(
    PostListRoute::class.simpleName.toString(),
    AlbumsListRoute::class.simpleName.toString(),
    TodosListRoute::class.simpleName.toString(),
    UsersListRoute::class.simpleName.toString(),
)

val EmptyBottomBars = listOf(
    ImagePagerRoute::class.simpleName.toString(),
    SplashScreenRoute::class.simpleName.toString()
)