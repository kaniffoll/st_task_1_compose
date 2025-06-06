package com.example.task_1_compose.repositories

import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.data.postsList

object PostRepository {
    private val posts = postsList

    fun getSomePosts(currentPage: Int, postsPerPage: Int): List<Post> {
        val startIndex = currentPage * postsPerPage
        val endIndex = minOf(startIndex + postsPerPage, posts.size)
        if (startIndex >= endIndex) {
            return emptyList()
        }
        return postsList.subList(startIndex, endIndex)
    }

    fun toggleLike(id: Int) {
        posts.replaceAll { post ->
            if (post.id == id) post.copy(isLiked = !post.isLiked) else post
        }
    }

    fun getPostById(id: Int): Post {
        return posts.find { it.id == id } ?: throw (RuntimeException("Post Id == null"))
    }
}