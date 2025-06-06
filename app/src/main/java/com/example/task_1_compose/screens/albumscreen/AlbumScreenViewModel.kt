package com.example.task_1_compose.screens.albumscreen

import androidx.lifecycle.ViewModel
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.data.dataclasses.Photo
import com.example.task_1_compose.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumScreenViewModel(id: Int) : ViewModel() {
    private val albumsRepository = AlbumsRepository()
    private val _fullAlbum = albumsRepository.getAlbumById(id)
    private var _albumState = MutableStateFlow(Album(id, _fullAlbum.name, emptyList()))
    val albumsState = _albumState.asStateFlow()
    private var _canLoadMore = MutableStateFlow(true)
    val canLoadMore = _canLoadMore.asStateFlow()

    private var currentPage = 0
    private val photosPerPage = 10

    init {
        updateAlbum()
    }

    fun updateAlbum() {
        if (!_canLoadMore.value) {
            return
        }
        val newPhotos = _albumState.value.photos + loadSomePhotos()
        _albumState.value = _albumState.value.copy(photos = newPhotos)
    }

    private fun loadSomePhotos(): List<Photo> {
        val startIndex = currentPage * photosPerPage
        val endIndex = minOf(startIndex + photosPerPage, _fullAlbum.photos.size)
        if (startIndex >= endIndex) {
            _canLoadMore.value = false
            return emptyList()
        }
        currentPage++
        return _fullAlbum.photos.subList(startIndex, endIndex)
    }

}