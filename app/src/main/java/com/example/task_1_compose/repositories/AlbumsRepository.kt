package com.example.task_1_compose.repositories

import com.example.task_1_compose.data.albumsList
import com.example.task_1_compose.data.dataclasses.Album

class AlbumsRepository {
    private val albums = albumsList

    fun getAlbums(): List<Album> {
        return albums
    }

    fun getAlbumById(id: Int): Album {
        return albums[id]
    }
}