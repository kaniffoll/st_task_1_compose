package com.example.domain.data.dataclasses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,

    val userId: Int,

    val title: String,

    @SerialName("body")
    val description: String,

    val comments: MutableList<Comment> = mutableListOf(),

    var isLiked: Boolean = false
) : java.io.Serializable
