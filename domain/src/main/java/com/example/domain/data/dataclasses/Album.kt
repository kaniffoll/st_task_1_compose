package com.example.domain.data.dataclasses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,

    @SerialName("title")
    val name: String,

    val photos: MutableList<Photo> = mutableListOf()
)