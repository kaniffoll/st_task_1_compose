package com.example.task_1_compose.ui.screens.album

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.domain.data.dataclasses.Album
import com.example.task_1_compose.navigation.ImagePagerRoute
import com.example.task_1_compose.ui.components.cards.PhotoCard
import com.example.task_1_compose.ui.components.containers.LoadMoreList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AlbumScreen(
    navController: NavController,
    albumId: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val viewModel =
        hiltViewModel<AlbumScreenViewModel, AlbumScreenViewModel.AlbumScreenViewModelFactory> { factory ->
            factory.create(albumId)
        }

    val photos by viewModel.photos.collectAsState()

    LoadMoreList(
        onLoadMore = { viewModel.loadNextAlbumPhotos() },
        isPaginationFinished = { !viewModel.canLoadMorePhotos() },
        scope = viewModel.viewModelScope,
        data = photos
    ) { index ->
        val currentPhotos = viewModel.currentPhotos()
        PhotoCard(
            modifier = Modifier,
            id = index,
            photo = currentPhotos[index],
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope
        ) {
            navController.navigate(
                ImagePagerRoute(
                    album = Album(
                        id = albumId,
                        title = "",
                        photos = currentPhotos.toMutableList(),
                    ), initialImage = index
                )
            )
        }
    }
}
