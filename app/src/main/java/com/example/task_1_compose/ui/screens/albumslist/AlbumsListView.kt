package com.example.task_1_compose.ui.screens.albumslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.task_1_compose.navigation.AlbumScreenRoute
import com.example.task_1_compose.ui.components.cards.AlbumCard
import com.example.task_1_compose.ui.components.containers.LoadMoreList

@Composable
fun AlbumsList(navController: NavController) {
    val viewModel: AlbumsListViewModel = hiltViewModel()

    val albums by viewModel.albums.collectAsState()

    LoadMoreList(
        onLoadMore = { viewModel.loadNextAlbums() },
        isPaginationFinished = { !viewModel.canLoadMoreAlbums() },
        scope = viewModel.viewModelScope,
        data = albums
    ) { index ->
        val currentAlbums = viewModel.currentAlbums()
        val album = currentAlbums[index]

        AlbumCard(
            album = album
        ) {
            navController.navigate(AlbumScreenRoute(album.id))
        }
    }

}
