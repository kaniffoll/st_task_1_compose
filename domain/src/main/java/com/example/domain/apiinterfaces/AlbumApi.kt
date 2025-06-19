package com.example.domain.apiinterfaces

import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Photo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumApi {
    @GET("albums")
    suspend fun getAlbums(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<Album>

    @GET("albums/{id}/photos")
    suspend fun getPhotos(
        @Path("id") albumId: Int,
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<Photo>
}