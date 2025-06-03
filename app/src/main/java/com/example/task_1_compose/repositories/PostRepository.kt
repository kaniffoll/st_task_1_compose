package com.example.task_1_compose.repositories

import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.data.postsList

class PostRepository {
    private val posts = postsList
    private var currentPage = 0
    private val postsPerPage = 10
    fun getSomePosts(): List<Post> {
        val startIndex = currentPage * postsPerPage
        val endIndex = minOf(startIndex + postsPerPage, posts.size)
        if (startIndex >= endIndex) return emptyList()
        currentPage++
        return postsList.subList(startIndex, endIndex)
    }

    fun resetPagination() {
        currentPage = 0
    }
}