package com.example.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val comments: MutableList<Comment> = mutableListOf(),
    var isLiked: Boolean = false
) : java.io.Serializable
