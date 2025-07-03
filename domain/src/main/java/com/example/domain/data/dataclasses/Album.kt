package com.example.domain.data.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Album(
    @PrimaryKey
    val id: Int,
    val title: String,
    val photos: MutableList<Photo> = mutableListOf()
)