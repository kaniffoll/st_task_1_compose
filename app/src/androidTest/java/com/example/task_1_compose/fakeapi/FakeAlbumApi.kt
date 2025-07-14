package com.example.task_1_compose.fakeapi

import com.example.domain.api.AlbumApi
import com.example.domain.data.Album
import com.example.domain.data.Photo

class FakeAlbumApi: AlbumApi {
    private val albums = listOf<Album>()

    override suspend fun getAlbums(start: Int, limit: Int): List<Album> {
        return albums
    }

    override suspend fun getPhotos(albumId: Int, start: Int, limit: Int): List<Photo> {
        return albums[albumId].photos
    }
}