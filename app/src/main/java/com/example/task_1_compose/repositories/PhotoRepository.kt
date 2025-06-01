package com.example.task_1_compose.repositories

import com.example.task_1_compose.data.albumsList
import com.example.task_1_compose.data.dataclasses.Photo

class PhotoRepository {
    private val albums = albumsList
    private var currentPage = 0
    private val photosPerPage = 10

    fun getAlbumsNames(): List<Pair<String, Int>> {
        return albums.map { it.name to it.id }
    }

    fun getSomePhotos(id: Int) : List<Photo> {
        val startIndex = currentPage * photosPerPage
        val endIndex = minOf(startIndex + photosPerPage, albums[id].photos.size)
        if (startIndex >= endIndex) return emptyList()
        currentPage++
        return albums[id].photos.subList(startIndex, endIndex)
    }

    fun resetPagination() {
        currentPage = 0
    }
}