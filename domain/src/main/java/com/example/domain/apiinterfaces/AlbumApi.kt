package com.example.domain.apiinterfaces

import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Photo
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") albumId: Int): Album

    @GET("albums/{id}/photos")
    suspend fun getPhotos(@Path("id") albumId: Int): List<Photo>
}