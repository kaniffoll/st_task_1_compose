package com.example.domain.repositories

import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.mocks.postsList
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import kotlinx.coroutines.delay

class PostsRepository {
    private var posts = postsList

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        delay(1000L)
        return try {
            if (currentPage * POSTS_PER_PAGE > posts.size) {
                posts
            } else {
                posts.subList(0, currentPage * POSTS_PER_PAGE)
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun loadPostCommentsById(id: Int, currentPage: Int): List<Comment>? {
        delay(1000L)
        val currentPost = posts.firstOrNull { it.id == id }
        return try {
            currentPost?.comments?.subList(currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE)
        } catch (e: Exception) {
            null
        }
    }
}
