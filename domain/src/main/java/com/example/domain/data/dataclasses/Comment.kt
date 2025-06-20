package com.example.domain.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val name: String,
    val body: String
) : java.io.Serializable