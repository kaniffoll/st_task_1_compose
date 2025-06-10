package com.example.domain.repositories

import com.example.domain.data.dataclasses.Post
import com.example.domain.data.postsList
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE

class PostsRepository {
    private val posts = postsList

    fun loadNextPosts(currentPage: Int): List<Post> {
        val endIndex = minOf(currentPage * POSTS_PER_PAGE + POSTS_PER_PAGE, posts.size)

        return postsList.subList(0, endIndex)
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
