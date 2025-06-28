package com.example.task_1_compose.fakeapi

import com.example.domain.apiinterfaces.PostApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post
import kotlinx.coroutines.delay

class FakePostApi : PostApi {
    private val posts = List(20) { index ->
        Post(
            id = index + 1,
            userId = (index % 5) + 1,
            title = "Post Title ${index + 1}",
            body = "Post Text ${index + 1}"
        )
    }

    override suspend fun getPosts(start: Int, limit: Int): List<Post> {
        delay(1000L)
        return posts.subList(0, start + limit)
    }

    override suspend fun getComments(postId: Int, start: Int, limit: Int): List<Comment> {
        delay(1000L)
        return posts[postId].comments.subList(start, start + limit)
    }
}