package com.example.domain.api.album

import com.example.domain.data.Album
import com.example.domain.data.Photo

interface AlbumApi {
    suspend fun getAlbums(start: Int, limit: Int): List<Album>?
    suspend fun getPhotos(albumId: Int, start: Int, limit: Int): List<Photo>?
}