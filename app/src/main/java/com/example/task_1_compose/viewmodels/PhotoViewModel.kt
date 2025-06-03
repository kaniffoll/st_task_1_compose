package com.example.task_1_compose.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.data.dataclasses.Photo
import com.example.task_1_compose.repositories.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhotoViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel() {
    private val _albumsState = MutableStateFlow<List<Pair<String, Int>>>(emptyList())
    val albumsState: StateFlow<List<Pair<String, Int>>> = _albumsState.asStateFlow()
    private var currentPhotos by mutableStateOf(emptyList<Photo>())
    private val _currentAlbumState = MutableStateFlow(Album(-1, "", emptyList()))
    val currentAlbumState = _currentAlbumState.asStateFlow()
    private var activeAlbumId: Int? = null

    init {
        photoRepository.resetPagination()
        currentPhotos = emptyList()
        loadAlbums()
    }

    private fun loadAlbums() {
        _albumsState.value = photoRepository.getAlbumsNames()
    }

    fun updateCurrentAlbum(id: Int) {
        if (id != activeAlbumId) {
            currentPhotos = emptyList()
            photoRepository.resetPagination()
            activeAlbumId = id
            currentPhotos = photoRepository.getSomePhotosById(id)
        } else {
            currentPhotos += photoRepository.getSomePhotosById(id)
        }
        val pair = _albumsState.value.firstOrNull { it.second == id }
            ?: return

        _currentAlbumState.value = Album(
            id = pair.second,
            name = pair.first,
            photos = currentPhotos
        )
    }
}