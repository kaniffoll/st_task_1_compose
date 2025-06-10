package com.example.domain.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val username: String,
    val comments: MutableList<Comment>,
    val address: String,
    val phone: String
)