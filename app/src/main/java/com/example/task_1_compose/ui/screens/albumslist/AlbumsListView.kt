package com.example.task_1_compose.ui.screens.albumslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.statefuldata.canLoadMore
import com.example.task_1_compose.navigation.AlbumScreenRoute
import com.example.task_1_compose.ui.components.cards.AlbumCard
import com.example.task_1_compose.ui.components.containers.LoadMoreList
import com.example.task_1_compose.ui.screens.albumslist.store.AlbumsListIntent
import com.example.task_1_compose.ui.screens.albumslist.store.AlbumsListStoreFactory

@Composable
fun AlbumsList(navController: NavController) {
    val storeFactory = AlbumsListStoreFactory(DefaultStoreFactory(), LocalContext.current)

    val store = remember { storeFactory.create() }

    val state = store.states.collectAsState(initial = store.state)

    val scope = rememberCoroutineScope()

    LoadMoreList(
        onLoadMore = { store.accept(AlbumsListIntent.LoadNextAlbums) },
        isPaginationFinished = { !state.value.statefulData.canLoadMore(ALBUMS_PER_PAGE) },
        scope = scope,
        data = state.value.statefulData
    ) { index ->
        val currentAlbums = state.value.currentAlbums
        val album = currentAlbums[index]

        AlbumCard(
            album = album
        ) {
            navController.navigate(AlbumScreenRoute(album.id))
        }
    }
}
