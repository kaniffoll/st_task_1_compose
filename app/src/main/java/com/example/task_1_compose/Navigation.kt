package com.example.task_1_compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.task_1_compose.albums.AlbumScreen
import com.example.task_1_compose.albums.AlbumsList
import com.example.task_1_compose.albums.ImagePager
import com.example.task_1_compose.data.AlbumScreenRoute
import com.example.task_1_compose.data.AlbumsListRoute
import com.example.task_1_compose.data.ImagePagerRoute
import com.example.task_1_compose.data.PostListRoute
import com.example.task_1_compose.data.PostScreenRoute
import com.example.task_1_compose.data.TodosListRoute
import com.example.task_1_compose.data.UserScreenRoute
import com.example.task_1_compose.data.UsersListRoute
import com.example.task_1_compose.posts.PostList
import com.example.task_1_compose.posts.PostScreen
import com.example.task_1_compose.todos.TodosList
import com.example.task_1_compose.users.UserScreen
import com.example.task_1_compose.users.UsersList

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PostListRoute) {
        composable<PostListRoute> {
            PostList(navController)
        }

        composable<PostScreenRoute> {
            val args = it.toRoute<PostScreenRoute>()
            PostScreen(post = args.post, navController)
        }

        composable<AlbumsListRoute> {
            AlbumsList(navController)
        }

        composable<AlbumScreenRoute> {
            val args = it.toRoute<AlbumScreenRoute>()
            AlbumScreen(album = args.album, navController)
        }

        composable<ImagePagerRoute> {
            val args = it.toRoute<ImagePagerRoute>()
            ImagePager(
                album = args.album,
                initialImage = args.initialImage,
                navController
            )
        }

        composable<TodosListRoute> {
            TodosList(navController)
        }

        composable<UsersListRoute> {
            UsersList(navController)
        }

        composable<UserScreenRoute> {
            val args = it.toRoute<UserScreenRoute>()
            UserScreen(user = args.user, navController)
        }
    }
}

