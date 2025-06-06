package com.example.task_1_compose.navigation

import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.data.dataclasses.User
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute

@Serializable
data object SplashScreenRoute : AppRoute

@Serializable
data object PostListRoute : AppRoute

@Serializable
data object AlbumsListRoute : AppRoute

@Serializable
data object TodosListRoute : AppRoute

@Serializable
data object UsersListRoute : AppRoute

@Serializable
data class AlbumScreenRoute(
    val id: Int
) : AppRoute

@Serializable
data class PostScreenRoute(
    val post: Post
) : AppRoute

@Serializable
data class ImagePagerRoute(
    val initialImage: Int,
    val album: Album
) : AppRoute

@Serializable
data class UserScreenRoute(
    val user: User
) : AppRoute