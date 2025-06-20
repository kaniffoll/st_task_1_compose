package com.example.task_1_compose.ui.screens.album

import androidx.lifecycle.ViewModel
import com.example.domain.data.dataclasses.Photo
import com.example.domain.repositories.AlbumsRepository
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.resources.StringResources.LOADING_PHOTOS_ERROR
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumScreenViewModel(private val albumId: Int) : ViewModel() {
    private val albumsRepository = AlbumsRepository()

    private var _photos = MutableStateFlow<StatefulData<List<Photo>>>(LoadingData())
    val photos = _photos.asStateFlow()

    private var currentPage = 0

    fun canLoadMorePhotos(): Boolean {
        return _photos.value.canLoadMore(PHOTOS_PER_PAGE)
    }

    fun currentPhotos(): List<Photo> {
        return _photos.value.unwrap(defaultValue = emptyList())
    }

    suspend fun loadNextAlbumPhotos() {
        if (!canLoadMorePhotos()) {
            return
        }

        when (val newPhotos = albumsRepository.loadNextAlbumPhotos(albumId, currentPage)) {
            null -> _photos.value = ErrorData(LOADING_PHOTOS_ERROR)
            else -> {
                currentPage++
                _photos.value = SuccessData(currentPhotos() + newPhotos)
            }
        }
    }
}