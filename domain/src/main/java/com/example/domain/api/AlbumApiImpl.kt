package com.example.domain.api

import com.example.domain.data.Album
import com.example.domain.data.Photo
import com.example.domain.resources.AppUrls.ALBUMS
import com.example.domain.resources.AppUrls.BASE_URL
import com.example.domain.resources.AppUrls.PHOTOS
import com.example.domain.utilities.e
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class AlbumApiImpl(private val client: HttpClient) : AlbumApi {
    override suspend fun getPhotos(
        albumId: Int,
        start: Int,
        limit: Int
    ): List<Photo>? {
        return try {
            client.get {
                url("$BASE_URL$ALBUMS/$albumId$PHOTOS")
                parameter("start", start)
                parameter("limit", limit)
            }.body()
        } catch (e: Exception) {
            e.e()
            null
        }
    }

    override suspend fun getAlbums(
        start: Int,
        limit: Int
    ): List<Album>? {
        return try {
            client.get {
                url("$BASE_URL$ALBUMS")
                parameter("start", start)
                parameter("limit", limit)
            }.body()
        } catch (e: Exception) {
            e.e()
            null
        }
    }
}