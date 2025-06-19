package com.example.domain.data.dataclasses

import androidx.annotation.DrawableRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("title")
    val name: String,
    @DrawableRes var photo: Int = 0
)
