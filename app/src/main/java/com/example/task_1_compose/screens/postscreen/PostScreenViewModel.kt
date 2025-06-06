package com.example.task_1_compose.screens.postscreen

import androidx.lifecycle.ViewModel
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.repositories.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostScreenViewModel(post: Post): ViewModel() {
    private var _postState = MutableStateFlow(Post(-1, "", "", ""))
    val postState = _postState.asStateFlow()

    init {
        _postState.value = post
    }

    fun toggleLike() {
        val currentIsLiked = _postState.value.isLiked
        _postState.value = _postState.value.copy(isLiked = !currentIsLiked)
        PostRepository.toggleLike(_postState.value.id)

    }
}