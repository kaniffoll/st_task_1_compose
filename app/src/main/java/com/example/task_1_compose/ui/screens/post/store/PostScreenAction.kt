package com.example.task_1_compose.ui.screens.post.store

sealed interface PostScreenAction {
    data object LoadInitialComments: PostScreenAction
}