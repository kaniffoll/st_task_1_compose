package com.example.domain.api

import com.example.domain.data.Comment
import com.example.domain.data.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {
    @GET("posts")
    suspend fun getPosts(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<Post>

    @GET("posts/{id}/comments")
    suspend fun getComments(
        @Path("id") postId: Int,
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<Comment>
}