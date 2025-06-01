package com.example.task_1_compose.data.dataclasses

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val name: String,
    @DrawableRes val photo: Int
)
