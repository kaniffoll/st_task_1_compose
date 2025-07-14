package com.example.task_1_compose.fakeapi

import com.example.domain.api.PostApi
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.testmocks.mockPosts
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