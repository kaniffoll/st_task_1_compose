package com.example.testmocks

import com.example.domain.data.Album
import com.example.domain.data.Photo
import com.example.domain.resources.imagesList

val mockAlbums = listOf(Album(1, "Album"))
val mockPhotos = listOf(Photo(""))
val mockAlbum = Album(
    id = 0,
    title = "Album",
    photos = imagesList.map {
        Photo(title = "", photo = it)
    }.toMutableList()
)
const val initAlbumPage = 0
const val mockAlbumId = 0