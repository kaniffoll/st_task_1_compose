package com.example.domain.repositories

import android.util.Log
import com.example.domain.RetrofitClient
import com.example.domain.apiinterfaces.AlbumApi
import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Photo
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.utilities.getRandomPainterRes

class AlbumsRepository {
    private val api: AlbumApi = RetrofitClient.albumsApi
    private val albums = mutableListOf<Album>()

    suspend fun loadNextAlbums(currentPage: Int): List<Album>? {
        try {
            val response = api.getAlbums(currentPage * ALBUMS_PER_PAGE, ALBUMS_PER_PAGE)
            albums.addAll(response)
        } catch (e: Exception) {
            Log.e("ALBUMS_REPOSITORY_LOAD_ALBUMS", e.toString())
            return null
        }
        return albums
    }

    suspend fun loadNextAlbumPhotos(albumId: Int, currentPage: Int): List<Photo>? {
        try {
            val response = api.getPhotos(albumId, currentPage * PHOTOS_PER_PAGE, PHOTOS_PER_PAGE)
            response.forEach{it.photo = getRandomPainterRes() }
            return response
        } catch (e: Exception) {
            Log.e("ALBUMS_REPOSITORY_LOAD_PHOTOS", e.toString())
            return null
        }
    }
}