package com.example.task_1_compose.screens.albumslist

import androidx.lifecycle.ViewModel
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumsListViewModel: ViewModel() {
    private val albumsRepository = AlbumsRepository()
    private val _albumsState = MutableStateFlow<List<Album>>(emptyList())
    val albumsState = _albumsState.asStateFlow()

    init {
        loadAlbums()
    }

    private fun loadAlbums() {
        _albumsState.value = albumsRepository.getAlbums()
    }

}