package com.example.domain.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val author: String,
    val message: String
) : java.io.Serializable