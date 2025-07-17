package com.example.task_1_compose.ui.screens.album.store

import com.example.domain.data.Photo
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

data class AlbumScreenState(
    val statefulData: StatefulData<List<Photo>> = LoadingData(),
    val currentPage: Int = 1,
    val currentPhotos: List<Photo> = emptyList()
)
