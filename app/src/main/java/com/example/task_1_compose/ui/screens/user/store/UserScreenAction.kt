package com.example.task_1_compose.ui.screens.user.store

sealed interface UserScreenAction {
    data object LoadInitialComments : UserScreenAction
}