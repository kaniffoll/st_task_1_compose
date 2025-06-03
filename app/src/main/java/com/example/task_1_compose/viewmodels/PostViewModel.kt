package com.example.task_1_compose.viewmodels

import androidx.lifecycle.ViewModel
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.repositories.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostViewModel(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _postsState = MutableStateFlow<List<Post>>(emptyList())
    val postsState: StateFlow<List<Post>> = _postsState.asStateFlow()
    private var allPostsLoaded = false
    private var _currentPostId = MutableStateFlow<Int?>(null)
    val currentPostId = _currentPostId.asStateFlow()

    init {
        loadFirstPosts()
    }

    private fun loadFirstPosts() {
        postRepository.resetPagination()
        val posts = postRepository.getSomePosts()
        _postsState.value = posts
    }

    fun loadMorePosts() {
        if (allPostsLoaded) return
        val posts = postRepository.getSomePosts()
        if (posts.isEmpty()) allPostsLoaded = true
        _postsState.value += posts
    }

    fun toggleLike(id: Int) {
        _postsState.value = _postsState.value.map {
            if( it.id == id ) it.copy(likedState = !it.likedState) else it
        }
    }

    fun setCurrentPostId(id: Int) {
        _currentPostId.value = id
    }
}