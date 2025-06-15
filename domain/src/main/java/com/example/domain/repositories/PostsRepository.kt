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

    suspend fun fetchData() {
        try {
            val response = api.getPosts()
            posts.addAll(response)
        } catch (e: Exception) {
            Log.e("POST_REPOSITORY_FETCH_DATA", e.toString())
            e.printStackTrace()
        }
    }

    fun loadNextPosts(currentPage: Int): List<Post> {
        val endIndex = minOf(currentPage * POSTS_PER_PAGE + POSTS_PER_PAGE, posts.size)

        return posts.subList(0, endIndex)
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

    fun getPostById(id: Int): Post {
        return posts.find { it.id == id } ?: throw (RuntimeException("Post Id == null"))
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
