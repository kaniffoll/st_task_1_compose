package com.example.task_1_compose.ui.screens.post.store

sealed interface PostScreenIntent {
    data object ToggleLike : PostScreenIntent
    data object LoadNextComments : PostScreenIntent
}