package com.example.domain.repositories

import com.example.domain.apiinterfaces.AlbumApi
import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Photo
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.utilities.getRandomPainterRes
import javax.inject.Inject

class AlbumsRepository @Inject constructor(private val api: AlbumApi) {
    private val albums = mutableListOf<Album>()

    suspend fun loadNextAlbums(currentPage: Int): List<Album>? {
        try {
            val response = api.getAlbums(currentPage * ALBUMS_PER_PAGE, ALBUMS_PER_PAGE)
            albums.addAll(response)
        } catch (e: Exception) {
            return null
        }
        return albums
    }

    suspend fun loadNextAlbumPhotos(albumId: Int, currentPage: Int): List<Photo>? {
        try {
            val response = api.getPhotos(albumId, currentPage * PHOTOS_PER_PAGE, PHOTOS_PER_PAGE)
            response.forEach { it.photo = getRandomPainterRes() }
            return response
        } catch (e: Exception) {
            return null
        }
    }
}