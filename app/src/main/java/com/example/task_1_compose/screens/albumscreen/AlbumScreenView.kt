package com.example.task_1_compose.screens.albumscreen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.OutlinedCustomCard
import com.example.task_1_compose.components.general.UserImageAndName
import com.example.task_1_compose.data.dataclasses.Photo
import com.example.task_1_compose.navigation.ImagePagerRoute
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AlbumScreen(
    navController: NavController,
    id: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val albumScreenViewModel: AlbumScreenViewModel = viewModel { AlbumScreenViewModel(id) }
    val album by albumScreenViewModel.albumsState.collectAsState()
    val canLoadMore by albumScreenViewModel.canLoadMore.collectAsState()
    val lazyListState = rememberLazyListState()
    val isAtBottom by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom && canLoadMore) {
            isLoading = true
            delay(1000L) //очевидно стоит убрать, нужно просто для демонстрации работы подгрузки
            albumScreenViewModel.updateAlbum()
            isLoading = false
        }
    }

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
    ) {
        items(album.photos.size) { id ->
            PhotoCard(
                modifier = Modifier,
                id = id,
                photo = album.photos[id],
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            ) {
                navController.navigate(
                    ImagePagerRoute(
                        album = album, initialImage = id
                    )
                )
            }
        }
        item {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(dimensionResource(R.dimen.loader_size))
                    )
                }
            }
        }
        item {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.padding_large)
                )
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PhotoCard(
    modifier: Modifier,
    id: Int,
    photo: Photo,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClick: () -> Unit
) {

    OutlinedCustomCard(
        onClick = onClick,
        modifier = modifier
    ) {
        UserImageAndName(username = photo.name)
        with(sharedTransitionScope) {
            Image(
                painter = painterResource(photo.photo),
                contentDescription = stringResource(R.string.photo),
                modifier = Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image-${id}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_large)
                    )
            )
        }
    }
}
