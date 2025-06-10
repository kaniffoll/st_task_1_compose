package com.example.task_1_compose.ui.screens.albumslist

import androidx.lifecycle.ViewModel
import com.example.domain.data.dataclasses.Album
import com.example.domain.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumsListViewModel: ViewModel() {
    private val albumsRepository = AlbumsRepository()

    private val _albums = MutableStateFlow<List<Album>>(emptyList())

    val albums = _albums.asStateFlow()

    init {
        loadAlbums()
    }

    private fun loadAlbums() {
        _albums.value = albumsRepository.getAlbums()
    }
}