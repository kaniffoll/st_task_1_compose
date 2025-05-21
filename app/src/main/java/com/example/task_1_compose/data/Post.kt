package com.example.task_1_compose.data

data class Post(
    val username: String,
    val title: String,
    val description: String,
    val comments: MutableList<Pair<String, String>> = mutableListOf(),
)
