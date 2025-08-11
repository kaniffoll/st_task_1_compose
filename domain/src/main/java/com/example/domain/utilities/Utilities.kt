package com.example.domain.utilities

import com.example.domain.resources.imagesList

fun getRandomPainterRes(): Int {
    return imagesList.random()
}