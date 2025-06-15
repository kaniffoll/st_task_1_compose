package com.example.task_1_compose.ui.screens.postslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.dataclasses.Post
import com.example.domain.repositories.PostsRepository
import com.example.task_1_compose.resources.AppSettings.POSTS_PER_PAGE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsListViewModel : ViewModel() {
    private val postsRepository = PostsRepository()

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private var currentPage = 0
    var canLoadMore = true

    private var lastOpenedPostId: Int? = null

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            postsRepository.fetchData()
            loadNextPosts()
        }
    }

    fun toggleLike(id: Int) {
        _posts.value = _posts.value.map {
            if (it.id == id) it.copy(isLiked = !it.isLiked) else it
        }

        postsRepository.toggleLike(id)
    }

    fun saveLastOpenedPostId(id: Int) {
        lastOpenedPostId = id
    }

    fun refreshPost() {
        val id = lastOpenedPostId ?: return
        val lastOpenedPost = postsRepository.getPostById(id)

        _posts.value = _posts.value.map {
            if (it.id == lastOpenedPostId) it.copy(isLiked = lastOpenedPost.isLiked) else it
        }

        lastOpenedPostId = null
    }

    fun loadNextPosts() {
        if (!canLoadMore) {
            return
        }

        val currentCount = _posts.value.size

        val newPosts = postsRepository.loadNextPosts(currentPage)

        _posts.value = newPosts
        canLoadMore = newPosts.size - currentCount == POSTS_PER_PAGE
        currentPage++
    }
}