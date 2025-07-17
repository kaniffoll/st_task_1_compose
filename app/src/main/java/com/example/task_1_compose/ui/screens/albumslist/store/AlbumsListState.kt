package com.example.task_1_compose.ui.screens.albumslist.store

import com.example.domain.data.Album
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

data class AlbumsListState(
    val statefulData: StatefulData<List<Album>> = LoadingData(),
    val currentPage: Int = 1,
    val currentAlbums: List<Album> = emptyList()
)
