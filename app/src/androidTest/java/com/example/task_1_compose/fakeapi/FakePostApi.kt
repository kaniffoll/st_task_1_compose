package com.example.task_1_compose.fakeapi

import com.example.domain.apiinterfaces.PostApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post
import com.example.domain.resources.mocks.mockPosts
import kotlinx.coroutines.delay

class FakePostApi : PostApi {
    override suspend fun getPosts(start: Int, limit: Int): List<Post> {
        delay(1000L)
        return mockPosts.subList(0, start + limit)
    }

    override suspend fun getComments(postId: Int, start: Int, limit: Int): List<Comment> {
        delay(1000L)
        return mockPosts[postId].comments.subList(start, start + limit)
    }
}