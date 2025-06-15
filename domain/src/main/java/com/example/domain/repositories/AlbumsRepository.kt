package com.example.domain.repositories

import android.util.Log
import com.example.domain.RetrofitClient
import com.example.domain.apiinterfaces.AlbumApi
import com.example.domain.data.albumsList
import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Photo
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE

class AlbumsRepository {
    private val api: AlbumApi = RetrofitClient.albumsApi
    private val albums = albumsList

    suspend fun getAlbums(): List<Album>? {
        try {
            return api.getAlbums()
        } catch (e: Exception) {
            Log.e("GET_ALBUMS", e.toString())
            return null
        }
    }

    fun getAlbumById(id: Int): Album {
        return albums[id]
    }

    fun loadNextAlbumPhotos(albumId: Int, currentPage: Int): List<Photo> {
        val album = getAlbumById(albumId)
        val endIndex = minOf(currentPage * PHOTOS_PER_PAGE + PHOTOS_PER_PAGE, album.photos.size)

        return album.photos.subList(0, endIndex)
    }
}