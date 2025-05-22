package com.example.task_1_compose.data

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val username: String,
    val title: String,
    val description: String,
    val comments: MutableList<Comment> = mutableListOf()
) : java.io.Serializable
