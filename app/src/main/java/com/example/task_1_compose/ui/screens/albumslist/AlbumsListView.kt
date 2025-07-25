package com.example.task_1_compose.ui.screens.albumslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.statefuldata.canLoadMore
import com.example.task_1_compose.navigation.AlbumScreenRoute
import com.example.task_1_compose.ui.components.cards.AlbumCard
import com.example.task_1_compose.ui.components.containers.LoadMoreList
import com.example.task_1_compose.ui.screens.albumslist.store.AlbumsListComponent
import com.example.task_1_compose.ui.screens.albumslist.store.AlbumsListIntent

@Composable
fun AlbumsList(
    component: AlbumsListComponent,
    navController: NavController
) {
    val store = component.store

    val state = store.states.collectAsState(initial = store.state)

    val scope = rememberCoroutineScope()

    LoadMoreList(
        onLoadMore = { store.accept(AlbumsListIntent.LoadNextAlbums) },
        isPaginationFinished = { !state.value.albums.canLoadMore(ALBUMS_PER_PAGE) },
        scope = scope,
        data = state.value.albums
    ) { index, list ->
        val album = list[index]

        AlbumCard(
            album = album
        ) {
            navController.navigate(AlbumScreenRoute(album.id))
        }
    }
}
