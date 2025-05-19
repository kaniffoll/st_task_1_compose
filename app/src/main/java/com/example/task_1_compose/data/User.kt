package com.example.task_1_compose.data

data class User(
    val name: String,
    val username: String,
    val comments: MutableList<String>,
    val address: String,
    val phone: String
)