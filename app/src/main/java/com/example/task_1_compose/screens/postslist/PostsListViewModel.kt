package com.example.task_1_compose.screens.postslist

import androidx.lifecycle.ViewModel
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.repositories.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostsListViewModel : ViewModel() {
    private val _postsState = MutableStateFlow<List<Post>>(emptyList())
    val postsState: StateFlow<List<Post>> = _postsState.asStateFlow()
    private var _canLoadMore = MutableStateFlow(true)
    val canLoadMore = _canLoadMore.asStateFlow()
    private var currentPage = 0
    private val postsPerPage = 10
    private var lastOpenedPostId: Int? = null

    init {
        loadFirstPosts()
    }

    private fun resetPagination() {
        currentPage = 0
    }

    private fun loadFirstPosts() {
        resetPagination()
        val posts = PostRepository.getSomePosts(currentPage, postsPerPage)
        if (posts.isNotEmpty()) {
            currentPage++
        }
        _postsState.value = posts
    }

    fun loadMorePosts() {
        if (!_canLoadMore.value) {
            return
        }
        val posts = PostRepository.getSomePosts(currentPage, postsPerPage)
        if (posts.isEmpty()) {
            _canLoadMore.value = false
        } else {
            currentPage++
        }
        _postsState.value += posts
    }

    fun toggleLike(id: Int) {
        _postsState.value = _postsState.value.map {
            if (it.id == id) it.copy(isLiked = !it.isLiked) else it
        }
        PostRepository.toggleLike(id)
    }

    fun saveLastOpenedPostId(id: Int) {
        lastOpenedPostId = id
    }

    fun refreshPost() {
        val id = lastOpenedPostId ?: return
        val lastOpenedPost = PostRepository.getPostById(id)
        _postsState.value = _postsState.value.map {
            if (it.id == lastOpenedPostId) it.copy(isLiked = lastOpenedPost.isLiked) else it
        }
        lastOpenedPostId = null
    }
}