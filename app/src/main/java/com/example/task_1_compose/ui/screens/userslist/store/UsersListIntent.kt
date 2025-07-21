package com.example.task_1_compose.ui.screens.userslist.store

sealed interface UsersListIntent {
    data object LoadUsers : UsersListIntent
}