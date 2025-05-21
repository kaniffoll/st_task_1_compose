package com.example.task_1_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.task_1_compose.albums.AlbumScreen
import com.example.task_1_compose.albums.AlbumsList
import com.example.task_1_compose.albums.ImagePager
import com.example.task_1_compose.posts.PostList
import com.example.task_1_compose.posts.PostScreen
import com.example.task_1_compose.todos.TodosList
import com.example.task_1_compose.users.UserScreen
import com.example.task_1_compose.users.UsersList

enum class Screen {
    PostList,
    PostScreen,
    AlbumsList,
    AlbumScreen,
    ImagePager,
    TodosList,
    UsersList,
    UserScreen
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PostList.name) {
        composable(route = Screen.PostList.name) {
            PostList(navController)
        }
        composable(
            route = Screen.PostScreen.name + "/{index}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            PostScreen(navController = navController, index = it.arguments?.getInt("index") ?: 0)
        }
        composable(route = Screen.AlbumsList.name) {
            AlbumsList(navController)
        }
        composable(
            route = Screen.AlbumScreen.name + "/{index}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            AlbumScreen(navController = navController, index = it.arguments?.getInt("index") ?: 0)
        }
        composable(
            route = Screen.ImagePager.name + "/{albumIndex}/{initialImage}",
            arguments = listOf(
                navArgument("albumIndex") {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("initialImage") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            ImagePager(
                albumIndex = it.arguments?.getInt("albumIndex") ?: 0,
                initialImage = it.arguments?.getInt("initialImage") ?: 0,
                navController = navController
            )
        }
        composable(route = Screen.TodosList.name) {
            TodosList(navController)
        }
        composable(
            route = Screen.UserScreen.name + "/{index}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            UserScreen(index = it.arguments?.getInt("index") ?: 0, navController)
        }
        composable(route = Screen.UsersList.name) {
            UsersList(navController)
        }
    }
}

