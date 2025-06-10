package com.example.domain.data.dataclasses

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val name: String,
    @DrawableRes val photo: Int
)
