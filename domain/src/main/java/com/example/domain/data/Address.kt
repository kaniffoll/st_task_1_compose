package com.example.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val street: String = "",
    val suite: String = "",
    val city: String = "",
)
