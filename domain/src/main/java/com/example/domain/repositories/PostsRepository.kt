package com.example.domain.repositories

import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.mocks.postsList
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import kotlinx.coroutines.delay

class PostsRepository {
    private var allCommentsLoaded = false

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        delay(1000L)
        return try {
            if (currentPage * POSTS_PER_PAGE > postsList.size) {
                postsList
            } else {
                postsList.subList(0, currentPage * POSTS_PER_PAGE)
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun loadPostCommentsById(id: Int, currentPage: Int): List<Comment>? {
        delay(1000L)
        val currentPost = postsList.firstOrNull { it.id == id } ?: return null
        try {
            val startIndex = currentPage * COMMENTS_PER_PAGE
            val endIndex = (startIndex + COMMENTS_PER_PAGE).coerceAtMost(currentPost.comments.size)

            if (startIndex >= currentPost.comments.size) {
                allCommentsLoaded = true
                return emptyList()
            }
            return currentPost.comments.subList(startIndex, endIndex)
        } catch (e: Exception) {
            return null
        }
    }
}
