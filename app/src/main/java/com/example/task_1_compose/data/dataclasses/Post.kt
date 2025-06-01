package com.example.task_1_compose.data.dataclasses

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val username: String,
    val title: String,
    val description: String,
    val comments: MutableList<Comment> = mutableListOf(),
    var isLiked: Boolean = false
) : java.io.Serializable {
    var likedState by mutableStateOf(isLiked)
}
