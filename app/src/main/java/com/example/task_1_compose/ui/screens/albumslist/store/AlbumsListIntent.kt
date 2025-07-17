package com.example.task_1_compose.ui.screens.albumslist.store

sealed interface AlbumsListIntent {
    data object LoadNextAlbums : AlbumsListIntent
}