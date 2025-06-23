package com.example.domain.repositories

import androidx.compose.runtime.mutableStateListOf
import com.example.domain.apiinterfaces.PostApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import com.example.domain.utilities.d
import com.example.domain.utilities.e
import javax.inject.Inject

class PostsRepository @Inject constructor(private val api: PostApi) {
    private var posts = mutableStateListOf<Post>()

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        try {
            val response = api.getPosts(currentPage * POSTS_PER_PAGE, POSTS_PER_PAGE)
            posts.addAll(response)
        } catch (e: Exception) {
            e.e()
            return null
        }
        return posts
    }

    fun toggleLike(id: Int) {
        id.d()
        posts.replaceAll { post ->
            if (post.id == id) {
                id.d()
                post.copy(isLiked = !post.isLiked)
            } else {
                post
            }
        }
    }

    suspend fun loadPostCommentsById(id: Int, currentPage: Int): List<Comment>? {
        try {
            val response = api.getComments(id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE)
            return response
        } catch (e: Exception) {
            e.e()
            return null
        }
    }
}
