package com.example.domain.apiinterfaces

import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}/comments")
    suspend fun getComments(
        @Path("id") postId: Int,
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<Comment>
}