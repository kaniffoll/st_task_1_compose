package com.example.domain.repositories

import androidx.compose.runtime.mutableStateListOf
import com.example.domain.api.post.PostApi
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE

class PostsRepository(private val api: PostApi) {
    private var posts = mutableStateListOf<Post>()

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        val response = api.getPosts(
                start = currentPage * POSTS_PER_PAGE, limit = POSTS_PER_PAGE
            ) ?: return null
        posts.addAll(response)
        return posts
    }

    suspend fun loadPostCommentsById(id: Int, currentPage: Int): List<Comment>? {
        return api.getComments(
            postId = id, start = currentPage * COMMENTS_PER_PAGE, limit = COMMENTS_PER_PAGE
        )
    }
}
