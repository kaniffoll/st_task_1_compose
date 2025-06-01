package com.example.task_1_compose.albums

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.PhotoCard
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.navigation.ImagePagerRoute
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AlbumScreen(
    album: Album, navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    loadMorePhotos: (String, Int) -> Album
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val lazyListState = rememberLazyListState()
    val isAtBottom by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }

    var currentAlbum by remember { mutableStateOf(album) }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom) {
            delay(1000L) //очевидно стоит убрать, нужно просто для демонстрации работы подгрузки
            currentAlbum = loadMorePhotos(album.name, album.id)
        }
    }

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
    ) {
        items(currentAlbum.photos.size) { id ->
            PhotoCard(
                modifier = Modifier,
                index = id,
                album = currentAlbum,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            ) {
                currentIndex = id
                navController.navigate(
                    ImagePagerRoute(
                        album = currentAlbum, initialImage = currentIndex
                    )
                )
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
