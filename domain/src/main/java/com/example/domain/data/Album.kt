package com.example.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,
    val title: String,
    val photos: MutableList<Photo> = mutableListOf()
)