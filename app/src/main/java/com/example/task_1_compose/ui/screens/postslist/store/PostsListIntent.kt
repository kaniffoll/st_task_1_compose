package com.example.task_1_compose.ui.screens.postslist.store

sealed interface PostsListIntent {
    data class ToggleLike(val id: Int) : PostsListIntent
    data object LoadNextPosts : PostsListIntent
}