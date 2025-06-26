package com.example.task_1_compose.fakeapi

import com.example.domain.apiinterfaces.PostApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post

class FakePostApi : PostApi {
    private val posts = listOf<Post>()

    override suspend fun getPosts(start: Int, limit: Int): List<Post> {
        return posts
    }

    override suspend fun getComments(postId: Int, start: Int, limit: Int): List<Comment> {
        return posts[postId].comments
    }
}