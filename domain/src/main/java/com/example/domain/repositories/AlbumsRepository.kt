package com.example.domain.repositories

import com.example.domain.data.albumsList
import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Photo
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE

class AlbumsRepository {
    private val albums = albumsList

    fun getAlbums(): List<Album> {
        return albums
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