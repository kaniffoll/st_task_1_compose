package com.example.task_1_compose.utilities

import androidx.compose.ui.graphics.Color
import com.example.domain.data.Address
import kotlin.random.Random

fun getAddressAsString(address: Address): String {
    return " " + address.city + ", " + address.street + ", " + address.suite
}

fun getRandomColorByUsername(username: String): Color {
    val seed = username.hashCode().toLong()
    val random = Random(seed)
    return Color(
        red = random.nextInt(1,256),
        green = random.nextInt(1, 256),
        blue = random.nextInt(1, 256)
    )
}
