package com.example.domain.data.dataclasses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Int,

    @SerialName("title")
    var text: String,

    var completed: Boolean = false
)
