package com.example.task_1_compose.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val username: String,
    val title: String,
    val description: String,
    val comments: MutableList<Comment> = mutableListOf(),
    var likedState: Boolean = false
) : java.io.Serializable
