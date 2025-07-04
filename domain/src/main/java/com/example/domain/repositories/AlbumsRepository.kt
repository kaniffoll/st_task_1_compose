package com.example.domain.repositories

import com.example.domain.apiinterfaces.AlbumApi
import com.example.domain.connectivityobserver.NetworkConnectivityObserver
import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Photo
import com.example.domain.db.daos.AlbumDao
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.utilities.getRandomPainterRes
import javax.inject.Inject

class AlbumsRepository @Inject constructor(
    private val api: AlbumApi,
    private val dao: AlbumDao,
    private val networkConnectivityObserver: NetworkConnectivityObserver,
) {
    private val albums = mutableListOf<Album>()

    suspend fun loadNextAlbums(currentPage: Int): List<Album>? {
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val response = api.getAlbums(currentPage * ALBUMS_PER_PAGE, ALBUMS_PER_PAGE)
                albums.addAll(response)
                dao.upsertAll(response)
            } catch (e: Exception) {
                return null
            }
        } else {
            var newAlbums = dao.getAllAlbums()
            newAlbums = newAlbums.filterNot { newAlbum -> albums.any { newAlbum.id == it.id } }
            albums.addAll(newAlbums)
        }
        return albums
    }

    suspend fun loadNextAlbumPhotos(albumId: Int, currentPage: Int): List<Photo>? {
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val response =
                    api.getPhotos(albumId, currentPage * PHOTOS_PER_PAGE, PHOTOS_PER_PAGE)
                val currentAlbum = dao.getAlbumById(albumId)
                dao.update(currentAlbum.copy(photos = response.toMutableList()))
                response.forEach { it.photo = getRandomPainterRes() }
                return response
            } catch (e: Exception) {
                return null
            }
        } else {
            val localAlbum = dao.getAlbumById(albumId)
            if (albums.isEmpty()) {
                albums.add(localAlbum)
                val localPhotos = localAlbum.photos
                localPhotos.forEach { it.photo = getRandomPainterRes() }
                return localPhotos
            } else {
                return emptyList()
            }
        }
    }
}