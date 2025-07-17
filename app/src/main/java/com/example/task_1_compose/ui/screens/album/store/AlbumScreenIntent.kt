package com.example.task_1_compose.ui.screens.album.store

sealed interface AlbumScreenIntent {
    data object LoadNextPhotos : AlbumScreenIntent
}