package com.example.domain.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,
    val name: String,
    val photos: List<Photo>
)