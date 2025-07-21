package com.example.task_1_compose.ui.screens.user.store

sealed interface UserScreenIntent {
    data object LoadComments: UserScreenIntent
}