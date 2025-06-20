package com.example.task_1_compose.ui.screens.albumslist

import androidx.lifecycle.ViewModel
import com.example.domain.data.dataclasses.Album
import com.example.domain.repositories.AlbumsRepository
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.resources.StringResources.LOADING_ALBUMS_ERROR
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumsListViewModel : ViewModel() {
    private val albumsRepository = AlbumsRepository()

    private val _albums = MutableStateFlow<StatefulData<List<Album>>>(LoadingData())
    val albums = _albums.asStateFlow()

    private var currentPage = 0

    fun canLoadMoreAlbums(): Boolean {
        return _albums.value.canLoadMore(ALBUMS_PER_PAGE)
    }

    fun currentAlbums(): List<Album> {
        return _albums.value.unwrap(defaultValue = emptyList())
    }

    suspend fun loadNextAlbums() {
        if (!canLoadMoreAlbums()) {
            return
        }

        when (val newAlbums = albumsRepository.loadNextAlbums(currentPage)) {
            null -> _albums.value = ErrorData(LOADING_ALBUMS_ERROR)
            else -> {
                currentPage++
                _albums.value = SuccessData(newAlbums)
            }
        }
    }
}