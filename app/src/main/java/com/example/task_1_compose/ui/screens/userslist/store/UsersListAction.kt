package com.example.task_1_compose.ui.screens.userslist.store

sealed interface UsersListAction {
    data object LoadUsers: UsersListAction
}