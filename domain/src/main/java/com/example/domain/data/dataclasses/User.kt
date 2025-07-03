package com.example.domain.data.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val comments: MutableList<Comment> = mutableListOf(),
    val address: Address,
    val phone: String
)