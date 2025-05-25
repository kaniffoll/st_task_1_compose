package com.example.task_1_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.task_1_compose.albums.AlbumScreen
import com.example.task_1_compose.albums.AlbumsList
import com.example.task_1_compose.albums.ImagePager
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.data.dataclasses.User
import com.example.task_1_compose.posts.PostList
import com.example.task_1_compose.posts.PostScreen
import com.example.task_1_compose.todos.TodosList
import com.example.task_1_compose.users.UserScreen
import com.example.task_1_compose.users.UsersList
import kotlin.reflect.typeOf

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = PostListRoute,
        modifier = modifier
    ) {
        composable<PostListRoute> {
            PostList(navController)
        }

        composable<PostScreenRoute>(
            typeMap = mapOf(
                typeOf<Post>() to CustomNavType.PostType
            )
        ) {
            val args = it.toRoute<PostScreenRoute>()
            PostScreen(post = args.post)
        }

        composable<AlbumsListRoute> {
            AlbumsList(navController)
        }

        composable<AlbumScreenRoute>(
            typeMap = mapOf(
                typeOf<Album>() to CustomNavType.AlbumType
            )
        ) {
            val args = it.toRoute<AlbumScreenRoute>()
            AlbumScreen(album = args.album, navController)
        }

        composable<ImagePagerRoute>(
            typeMap = mapOf(
                typeOf<Album>() to CustomNavType.AlbumType,
                typeOf<Int>() to NavType.IntType
            )
        ) {
            val args = it.toRoute<ImagePagerRoute>()
            ImagePager(
                album = args.album,
                initialImage = args.initialImage,
                navController
            )
        }

        composable<TodosListRoute> {
            TodosList()
        }

        composable<UsersListRoute> {
            UsersList(navController)
        }

        composable<UserScreenRoute>(
            typeMap = mapOf(
                typeOf<User>() to CustomNavType.UserType
            )
        ) {
            val args = it.toRoute<UserScreenRoute>()
            UserScreen(user = args.user)
        }
    }
}

