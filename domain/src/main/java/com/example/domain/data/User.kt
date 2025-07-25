package com.example.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val comments: MutableList<Comment> = mutableListOf(),
    val address: Address,
    val phone: String
)