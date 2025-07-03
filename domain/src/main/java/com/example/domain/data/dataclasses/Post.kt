package com.example.domain.data.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Post(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val comments: MutableList<Comment> = mutableListOf(),
    var isLiked: Boolean = false
) : java.io.Serializable
