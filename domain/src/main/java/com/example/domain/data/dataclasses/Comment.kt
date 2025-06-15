package com.example.domain.data.dataclasses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("name")
    val author: String,

    @SerialName("body")
    val message: String
) : java.io.Serializable