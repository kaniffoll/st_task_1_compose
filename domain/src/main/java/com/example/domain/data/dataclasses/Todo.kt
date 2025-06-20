package com.example.domain.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Int,
    var title: String,
    var completed: Boolean = false
)
