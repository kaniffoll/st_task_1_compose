package com.example.domain.repositories

import com.example.domain.apiinterfaces.PostApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import javax.inject.Inject

class PostsRepository @Inject constructor(private val api: PostApi) {

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        try {
            val response = api.getPosts(currentPage * POSTS_PER_PAGE, POSTS_PER_PAGE)
            return response
        } catch (e: Exception) {
            return null
        }
    }

    suspend fun loadPostCommentsById(id: Int, currentPage: Int): List<Comment>? {
        try {
            val response = api.getComments(id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE)
            return response
        } catch (e: Exception) {
            return null
        }
    }
}
