package com.example.domain.api

import com.example.domain.data.Comment
import com.example.domain.data.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}/comments")
    suspend fun getComments(
        @Path("id") userId: Int,
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<Comment>
}