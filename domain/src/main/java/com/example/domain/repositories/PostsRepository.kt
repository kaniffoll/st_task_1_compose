package com.example.domain.repositories

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.domain.RetrofitClient
import com.example.domain.apiinterfaces.PostApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE

class PostsRepository {
    private val api: PostApi = RetrofitClient.postApi
    private var posts = mutableStateListOf<Post>()

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        try {
            val response = api.getPosts(currentPage * POSTS_PER_PAGE, POSTS_PER_PAGE)
            posts.addAll(response)
        } catch (e: Exception) {
            Log.e("POST_REPOSITORY_FETCH_DATA", e.toString())
            return null
        }
        return posts
    }

    fun toggleLike(id: Int) {
        Log.d("TOGGLE_LIKE", id.toString())
        posts.replaceAll { post ->
            if (post.id == id) {
                Log.d("TOGGLE_LIKE_ASSIGN", id.toString())
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
            Log.e("POST_REPOSITORY", e.toString())
            return null
        }
    }
}
