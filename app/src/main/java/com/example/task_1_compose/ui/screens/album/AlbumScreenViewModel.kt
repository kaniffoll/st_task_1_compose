package com.example.task_1_compose.ui.screens.album

import androidx.lifecycle.ViewModel
import com.example.domain.repositories.AlbumsRepository
import com.example.task_1_compose.resources.AppSettings.PHOTOS_PER_PAGE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumScreenViewModel(private val albumId: Int) : ViewModel() {
    private val albumsRepository = AlbumsRepository()

    private var _album = MutableStateFlow(albumsRepository.getAlbumById(albumId).copy(photos = listOf()))

    val album = _album.asStateFlow()

    private var currentPage = 0
    var canLoadMore = true

    init {
        loadNextAlbumPhotos()
    }

    fun loadNextAlbumPhotos() {
        if (!canLoadMore) {
            return
        }

        val currentCount = _album.value.photos.size

        val newPhotos = albumsRepository.loadNextAlbumPhotos(
            albumId = albumId,
            currentPage = currentPage
        )

        _album.value = _album.value.copy(photos = newPhotos)
        canLoadMore = newPhotos.size - currentCount == PHOTOS_PER_PAGE
        currentPage++
    }
}