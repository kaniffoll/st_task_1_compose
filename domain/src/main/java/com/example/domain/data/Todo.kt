package com.example.domain.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Todo(
    @PrimaryKey
    val id: Int,
    var title: String,
    var completed: Boolean = false
)
