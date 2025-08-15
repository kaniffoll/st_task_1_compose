package com.example.domain.repositories

import com.example.domain.api.album.AlbumApi
import com.example.domain.data.Album
import com.example.domain.data.Photo
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.utilities.db.getAlbumById
import com.example.domain.utilities.db.loadAlbums
import com.example.domain.utilities.db.saveAlbumPhotos
import com.example.domain.utilities.db.saveAlbums
import com.example.domain.utilities.getRandomPainterRes
import com.example.domain.utilities.toAlbum
import io.realm.kotlin.Realm

class AlbumsRepository(
    private val api: AlbumApi,
    private val realm: Realm,
    private val connectivityObserver: NetworkConnectivityObserver,
) {
    private val albums = mutableListOf<Album>()

    fun clearLocalAlbums() = albums.clear()

    suspend fun loadNextAlbums(currentPage: Int): List<Album>? {
        if (connectivityObserver.isNetworkAvailable()) {
            val response = api.getAlbums(
                start = currentPage * ALBUMS_PER_PAGE, limit = ALBUMS_PER_PAGE
            ) ?: return null
            realm.saveAlbums(response)
            albums.addAll(response)
        } else {
            albums.clear()
            albums.addAll(
                realm.loadAlbums()
            )
        }
        return albums
    }

    suspend fun loadNextAlbumPhotos(albumId: Int, currentPage: Int): List<Photo>? {
        if (!connectivityObserver.isNetworkAvailable()) {
            val localAlbum = realm.getAlbumById(albumId)?.toAlbum() ?: return null
            if (albums.isNotEmpty()) return emptyList()
            albums.add(localAlbum)
            return generatePhotoResourcesForList(localAlbum.photos)
        }
        val response = api.getPhotos(
            albumId, start = currentPage * PHOTOS_PER_PAGE, limit = PHOTOS_PER_PAGE
        ) ?: return null

        realm.saveAlbumPhotos(albumId, response)
        return generatePhotoResourcesForList(response.toMutableList())
    }

    private fun generatePhotoResourcesForList(photos: MutableList<Photo>): MutableList<Photo> {
        photos.forEach { it.photo = getRandomPainterRes() }
        return photos
    }
}