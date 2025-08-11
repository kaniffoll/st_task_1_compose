package com.example.domain.repositories

import com.example.domain.api.album.AlbumApi
import com.example.domain.data.Album
import com.example.domain.data.Photo
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.utilities.getRandomPainterRes

class AlbumsRepository(private val api: AlbumApi) {
    private val albums = mutableListOf<Album>()

    suspend fun loadNextAlbums(currentPage: Int): List<Album>? {
        val response = api
            .getAlbums(
                start = currentPage * ALBUMS_PER_PAGE,
                limit = ALBUMS_PER_PAGE
            ) ?: return null
        albums.addAll(response)
        return albums
    }

    suspend fun loadNextAlbumPhotos(albumId: Int, currentPage: Int): List<Photo>? {
        val response = api
            .getPhotos(
                albumId,
                start = currentPage * PHOTOS_PER_PAGE,
                limit = PHOTOS_PER_PAGE
            ) ?: return null
        return generatePhotoResourcesForAlbum(
            Album(
                albumId,
                title = "",
                photos = response.toMutableList()
            )
        )
    }

    private fun generatePhotoResourcesForAlbum(album: Album): MutableList<Photo> {
        val photos = album.photos
        photos.forEach { it.photo = getRandomPainterRes() }
        return photos
    }
}