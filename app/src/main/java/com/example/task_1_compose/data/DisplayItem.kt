package com.example.task_1_compose.data

sealed class DisplayItem {
    data class UserItem(val user: User): DisplayItem()
    data class PostItem(val post: Post): DisplayItem()
}