package com.example.task_1_compose.ui.screens.album

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
    val viewModel: AlbumScreenViewModel = viewModel { AlbumScreenViewModel(albumId) }

    val album by viewModel.album.collectAsState()

    LoadMoreList(
        canLoadMore = viewModel.canLoadMore,
        onLoadMore = { viewModel.loadNextAlbumPhotos() },
        contentSize = album.photos.size
    ) { index ->
        PhotoCard(
            modifier = Modifier,
            id = index,
            photo = album.photos[index],
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope
        ) {
            navController.navigate(
                ImagePagerRoute(
                    album = album, initialImage = index
                )
            )
        }
    }
}
