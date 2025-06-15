package com.example.domain.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val street: String = "",
    val suite: String = "",
    val city: String = "",
)
