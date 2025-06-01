package com.example.task_1_compose.viewmodels

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
    private val _photoState = MutableStateFlow<List<Pair<String, Int>>>(emptyList())
    val photoState: StateFlow<List<Pair<String, Int>>> = _photoState.asStateFlow()
    private var currentPhotos = emptyList<Photo>()

    init {
        photoRepository.resetPagination()
        currentPhotos = emptyList()
        loadAlbums()
    }

    private fun loadAlbums() {
        _photoState.value = photoRepository.getAlbumsNames()
    }

    fun loadSomePhotosForAlbum(name: String, id: Int): Album {
        currentPhotos += photoRepository.getSomePhotos(id)
        return Album(
            id = id,
            name = name,
            photos = currentPhotos
        )
    }

    fun loadInitialPhotosForAlbum(name: String, id: Int): Album {
        photoRepository.resetPagination()
        currentPhotos = photoRepository.getSomePhotos(id)
        return Album(
            id = id,
            name = name,
            photos = currentPhotos
        )
    }
}