package com.example.domain.repositories

import com.example.domain.data.Album
import com.example.domain.data.Photo
import com.example.domain.mocks.albumsList
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import kotlinx.coroutines.delay

class AlbumsRepository {
    private var allPhotosLoaded = false

    suspend fun loadNextAlbums(currentPage: Int): List<Album>? {
        delay(1000L)
        return try {
            if (currentPage * ALBUMS_PER_PAGE > albumsList.size) {
                albumsList
            } else {
                albumsList.subList(0, currentPage * ALBUMS_PER_PAGE)
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun loadNextAlbumPhotos(albumId: Int, currentPage: Int): List<Photo>? {
        delay(1000L)
        val currentAlbum = albumsList.firstOrNull { it.id == albumId } ?: return null
        try {
            val startIndex = currentPage * ALBUMS_PER_PAGE
            val endIndex = (startIndex + ALBUMS_PER_PAGE).coerceAtMost(currentAlbum.photos.size)

            if (startIndex >= currentAlbum.photos.size) {
                allPhotosLoaded = true
                return emptyList()
            }
            return currentAlbum.photos.subList(startIndex, endIndex)
        } catch (e: Exception) {
            return null
        }
    }
}