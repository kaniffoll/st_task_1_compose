package com.example.domain.api.post

import com.example.domain.data.Comment
import com.example.domain.data.Post

interface PostApi {
    suspend fun getPosts(start: Int, limit: Int): List<Post>?
    suspend fun getComments(postId: Int, start: Int, limit: Int): List<Comment>?
}