package com.example.task_1_compose.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val username: String,
    val comments: MutableList<String>,
    val address: String,
    val phone: String
)