package com.example.task_1_compose.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val author: String,
    val message: String
) : java.io.Serializable