package com.example.task_1_compose.data

import androidx.annotation.DrawableRes

data class DrawRes(@DrawableRes val photo: Int)

data class Album(val name: String, val photos: List<Pair<String, DrawRes>>)