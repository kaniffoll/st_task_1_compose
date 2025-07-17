package com.example.task_1_compose.ui.screens.albumslist.store

import com.example.domain.data.Album
import com.example.domain.statefuldata.StatefulData

sealed interface AlbumsListMsg {
    data class NextAlbumsLoaded(val albums: List<Album>) : AlbumsListMsg
    data object AllAlbumsLoaded : AlbumsListMsg
    data class AlbumsLoadError(val statefulData: StatefulData<List<Album>>) : AlbumsListMsg
}