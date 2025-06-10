package com.example.domain.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val username: String,
    val title: String,
    val description: String,
    val comments: MutableList<Comment> = mutableListOf(),
    var isLiked: Boolean = false
) : java.io.Serializable
