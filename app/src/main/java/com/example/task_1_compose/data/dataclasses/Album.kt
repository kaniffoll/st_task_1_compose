package com.example.task_1_compose.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,
    val name: String,
    val photos: List<Photo>
)