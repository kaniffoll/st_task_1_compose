package com.example.task_1_compose.ui.screens.albumslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.dataclasses.Album
import com.example.domain.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumsListViewModel : ViewModel() {
    private val albumsRepository = AlbumsRepository()

    private val _albums = MutableStateFlow<List<Album>>(emptyList())

    val albums = _albums.asStateFlow()

    private var _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private var _loadingError = MutableStateFlow(false)
    val loadingError = _loadingError.asStateFlow()

    init {
        loadAlbums()
    }

    fun loadAlbums() {
        viewModelScope.launch {
            _isLoading.value = true
            _loadingError.value = false
            val albums = albumsRepository.getAlbums()
            if (albums == null) {
                _loadingError.value = true
            } else {
                _albums.value = albums
            }
            _isLoading.value = false
        }
    }
}