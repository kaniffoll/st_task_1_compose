package com.example.domain.utilities

import com.example.domain.data.imagesList


fun getRandomPainterRes(): Int {
    return imagesList.random()
}