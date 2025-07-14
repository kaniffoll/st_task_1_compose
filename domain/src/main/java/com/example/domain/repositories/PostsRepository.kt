package com.example.domain.repositories

import androidx.compose.runtime.mutableStateListOf
import com.example.domain.api.PostApi
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.db.daos.PostDao
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val api: PostApi,
    private val dao: PostDao,
    private val networkConnectivityObserver: NetworkConnectivityObserver,
) {
    private var posts = mutableStateListOf<Post>()

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val response = api.getPosts(currentPage * POSTS_PER_PAGE, POSTS_PER_PAGE)
                posts.addAll(response)
                dao.upsertAll(response)
            } catch (e: Exception) {
                return null
            }
        } else {
            var newPosts = dao.getAllPosts()
            newPosts = newPosts.filterNot { newPost -> posts.any { newPost.id == it.id } }
            posts.addAll(newPosts)
        }
        return posts
    }

    suspend fun loadPostCommentsById(id: Int, currentPage: Int): List<Comment>? {
        if (!networkConnectivityObserver.isNetworkAvailable()) {
            return emptyList()
        }
        try {
            val response =
                api.getComments(id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE)
            val currentPost = dao.getPostById(id)
            dao.update(currentPost.copy(comments = response.toMutableList()))
            return response
        } catch (e: Exception) {
            return null
        }
    }
}
