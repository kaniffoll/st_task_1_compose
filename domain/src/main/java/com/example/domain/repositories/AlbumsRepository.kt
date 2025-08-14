package com.example.domain.repositories

import com.example.domain.api.album.AlbumApi
import com.example.domain.data.Album
import com.example.domain.data.Photo
import com.example.domain.data.realm.AlbumRealm
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.utilities.getRandomPainterRes
import com.example.domain.utilities.toAlbum
import com.example.domain.utilities.toAlbumRealm
import com.example.domain.utilities.toPhotoRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy

class AlbumsRepository(
    private val api: AlbumApi,
    private val realm: Realm,
    private val connectivityObserver: NetworkConnectivityObserver,
) {
    private val albums = mutableListOf<Album>()
    private var allPhotosLoadedFromDB = false

    suspend fun loadNextAlbums(currentPage: Int): List<Album>? {
        if (connectivityObserver.isNetworkAvailable()) {
            val response = api.getAlbums(
                start = currentPage * ALBUMS_PER_PAGE, limit = ALBUMS_PER_PAGE
            ) ?: return null
            realm.write {
                response.forEach {
                    copyToRealm(
                        instance = it.toAlbumRealm(),
                        updatePolicy = UpdatePolicy.ALL
                    )
                }
            }
            albums.addAll(response)
        } else {
            albums.clear()
            albums.addAll(
                realm.query(AlbumRealm::class).find().map { it ->
                    it.toAlbum()
                }
            )
        }
        return albums
    }

    suspend fun loadNextAlbumPhotos(albumId: Int, currentPage: Int): List<Photo>? {
        if (!connectivityObserver.isNetworkAvailable()) {
            if (allPhotosLoadedFromDB) return emptyList()
            val album = realm.query(AlbumRealm::class, "id == $0", albumId)
                .first()
                .find()?.toAlbum() ?: return null
            allPhotosLoadedFromDB = true
            return generatePhotoResourcesForList(album.photos)
        }
        val response = api.getPhotos(
            albumId, start = currentPage * PHOTOS_PER_PAGE, limit = PHOTOS_PER_PAGE
        ) ?: return null

        realm.write {
            val album = this.query(AlbumRealm::class, "id == $0", albumId)
                .first()
                .find() ?: return@write
            val newPhotos = response.filter { newPhoto ->
                album.photos.none { it.title == newPhoto.title && it.photo == newPhoto.photo }
            }
            album.photos.addAll(newPhotos.map { it.toPhotoRealm() })
        }
        return generatePhotoResourcesForList(response.toMutableList())
    }

    private fun generatePhotoResourcesForList(photos: MutableList<Photo>): MutableList<Photo> {
        photos.forEach { it.photo = getRandomPainterRes() }
        return photos
    }
}