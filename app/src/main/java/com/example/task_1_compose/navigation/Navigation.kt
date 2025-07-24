package com.example.task_1_compose.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.domain.data.Album
import com.example.domain.data.Post
import com.example.domain.data.User
import com.example.domain.resources.AppUrls.DEEPLINK_DOMAIN
import com.example.task_1_compose.MainComponent
import com.example.task_1_compose.ui.screens.album.AlbumScreen
import com.example.task_1_compose.ui.screens.albumslist.AlbumsList
import com.example.task_1_compose.ui.screens.imagepager.ImagePager
import com.example.task_1_compose.ui.screens.post.PostScreen
import com.example.task_1_compose.ui.screens.postslist.PostsList
import com.example.task_1_compose.ui.screens.splash.SplashScreen
import com.example.task_1_compose.ui.screens.todoslist.TodosList
import com.example.task_1_compose.ui.screens.user.UserScreen
import com.example.task_1_compose.ui.screens.userslist.UsersList
import kotlin.reflect.typeOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    mainComponent: MainComponent,
    modifier: Modifier
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = SplashScreenRoute,
            modifier = modifier
        ) {
            composable<SplashScreenRoute> {
                SplashScreen(navController)
            }

            composable<PostListRoute> {
                PostsList(navController = navController, mainComponent.postsListComponent)
            }

            composable<PostScreenRoute>(
                typeMap = mapOf(
                    typeOf<Post>() to CustomNavType.PostType
                )
            ) {
                val args = it.toRoute<PostScreenRoute>()
                PostScreen(
                    post = args.post,
                    component = mainComponent.postScreenComponent
                )
            }
            composable<AlbumsListRoute> {
                AlbumsList(
                    component = mainComponent.albumsListComponent,
                    navController = navController
                )
            }

            composable<AlbumScreenRoute>(
                deepLinks = listOf(
                    navDeepLink<AlbumScreenRoute>(
                        basePath = "https://$DEEPLINK_DOMAIN/album"
                    )
                ),
            ) {
                val args = it.toRoute<AlbumScreenRoute>()
                AlbumScreen(
                    navController,
                    component = mainComponent.albumScreenComponent,
                    albumId = args.id,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
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
                    navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }

            composable<TodosListRoute> {
                TodosList(mainComponent.todosComponent)
            }

            composable<UsersListRoute> {
                UsersList(
                    navController,
                    mainComponent.usersListComponent,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }

            composable<UserScreenRoute>(
                typeMap = mapOf(
                    typeOf<User>() to CustomNavType.UserType
                )
            ) {
                val args = it.toRoute<UserScreenRoute>()
                UserScreen(
                    user = args.user,
                    component = mainComponent.userScreenComponent,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
        }
    }
}

