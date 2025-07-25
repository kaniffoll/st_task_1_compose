package com.example.task_1_compose.ui.screens.album

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.domain.data.Album
import com.example.domain.resources.AppSettings.LOADING_ITEM_FOR_SCREEN_DELAY
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.statefuldata.canLoadMore
import com.example.task_1_compose.navigation.ImagePagerRoute
import com.example.task_1_compose.ui.components.cards.PhotoCard
import com.example.task_1_compose.ui.components.containers.LoadMoreList
import com.example.task_1_compose.ui.screens.album.store.AlbumScreenComponent
import com.example.task_1_compose.ui.screens.album.store.AlbumScreenIntent
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AlbumScreen(
    navController: NavController,
    component: AlbumScreenComponent,
    albumId: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val store = component.store

    val state = store.states.collectAsState(initial = store.state)

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        store.accept(AlbumScreenIntent.InitializeAlbumScreen(albumId))
    }

    LoadMoreList(
        onLoadMore = {
            delay(LOADING_ITEM_FOR_SCREEN_DELAY)
            store.accept(AlbumScreenIntent.LoadNextPhotos)
        },
        isPaginationFinished = { !state.value.photos.canLoadMore(PHOTOS_PER_PAGE) },
        scope = scope,
        data = state.value.photos
    ) { index, list ->
        PhotoCard(
            modifier = Modifier,
            id = index,
            photo = list[index],
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope
        ) {
            navController.navigate(
                ImagePagerRoute(
                    album = Album(
                        id = albumId,
                        title = "",
                        photos = list.toMutableList(),
                    ), initialImage = index
                )
            )
        }
    }
}
