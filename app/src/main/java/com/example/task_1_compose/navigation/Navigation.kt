package com.example.task_1_compose.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.task_1_compose.albums.AlbumScreen
import com.example.task_1_compose.albums.AlbumsList
import com.example.task_1_compose.albums.ImagePager
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.data.dataclasses.User
import com.example.task_1_compose.posts.PostList
import com.example.task_1_compose.posts.PostScreen
import com.example.task_1_compose.repositories.PhotoRepository
import com.example.task_1_compose.repositories.PostRepository
import com.example.task_1_compose.repositories.TodosRepository
import com.example.task_1_compose.repositories.UsersRepository
import com.example.task_1_compose.splash_screen.SplashScreen
import com.example.task_1_compose.todos.TodosList
import com.example.task_1_compose.users.UserScreen
import com.example.task_1_compose.users.UsersList
import com.example.task_1_compose.viewmodels.PhotoViewModel
import com.example.task_1_compose.viewmodels.PostViewModel
import com.example.task_1_compose.viewmodels.TodosViewModel
import com.example.task_1_compose.viewmodels.UsersViewModel
import kotlin.reflect.typeOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier
) {
    val photoViewModel: PhotoViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PhotoViewModel(PhotoRepository()) as T
            }
        }
    )

    val postViewModel: PostViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PostViewModel(PostRepository()) as T
            }
        }
    )

    val todosViewModel: TodosViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TodosViewModel(TodosRepository()) as T
            }
        }
    )

    val usersViewModel: UsersViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UsersViewModel(UsersRepository()) as T
            }
        }
    )
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
                PostList(
                    postsState = postViewModel.postsState,
                    navController = navController,
                    onLikeClicked = { id ->
                        postViewModel.toggleLike(id)
                    },
                    getId = { id ->
                        postViewModel.setCurrentPostId(id)
                    }
                ) {
                    postViewModel.loadMorePosts()
                }
            }

            composable<PostScreenRoute> {
                PostScreen(
                    postsState = postViewModel.postsState,
                    postId = postViewModel.currentPostId
                ) { id ->
                    postViewModel.toggleLike(id)
                }
            }
            composable<AlbumsListRoute> {
                AlbumsList(
                    navController,
                    state = photoViewModel.albumsState
                ) { id ->
                    photoViewModel.updateCurrentAlbum(id)
                }
            }

            composable<AlbumScreenRoute> {
                AlbumScreen(
                    albumState = photoViewModel.currentAlbumState,
                    navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                ) { id ->
                    photoViewModel.updateCurrentAlbum(id)
                }
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
                TodosList(
                    state = todosViewModel.todosState,
                    removeAtIndex = { index ->
                        todosViewModel.removeTodoByIndex(index)
                    },
                    onTextChangeById = { index, text ->
                        todosViewModel.updateText(index, text)
                    }
                ) {
                    todosViewModel.addTodo()
                }
            }

            composable<UsersListRoute> {
                UsersList(
                    state = usersViewModel.usersState,
                    navController,
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
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
        }
    }
}

