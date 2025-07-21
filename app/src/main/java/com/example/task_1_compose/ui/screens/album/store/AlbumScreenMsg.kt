package com.example.task_1_compose.ui.screens.album.store

import com.example.domain.data.Photo
import com.example.domain.statefuldata.StatefulData

sealed interface AlbumScreenMsg {
    data class NextPhotosLoaded(val photos: List<Photo>) : AlbumScreenMsg
    data object AllPhotosLoaded : AlbumScreenMsg
    data class PhotosLoadError(val statefulData: StatefulData<List<Photo>>) : AlbumScreenMsg
}