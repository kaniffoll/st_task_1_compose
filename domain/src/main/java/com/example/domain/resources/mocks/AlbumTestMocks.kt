package com.example.domain.resources.mocks

import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Photo
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