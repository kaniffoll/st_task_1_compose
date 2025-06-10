package com.example.task_1_compose.ui.screens.post

import androidx.lifecycle.ViewModel
import com.example.domain.data.dataclasses.Post
import com.example.domain.repositories.PostsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostScreenViewModel(post: Post) : ViewModel() {
    private val postRepository = PostsRepository()

    private var _post = MutableStateFlow(post)

    val post = _post.asStateFlow()

    fun toggleLike() {
        val currentIsLiked = _post.value.isLiked
        _post.value = _post.value.copy(isLiked = !currentIsLiked)

        postRepository.toggleLike(_post.value.id)
    }
}