package com.example.task_1_compose.ui.screens.album.store

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.data.Photo
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

interface AlbumScreenStore : Store<AlbumScreenIntent, AlbumScreenState, Nothing>

sealed interface AlbumScreenIntent {
    data object LoadNextPhotos : AlbumScreenIntent
    data class InitializeAlbumScreen(val albumId: Int) : AlbumScreenIntent
}

sealed interface AlbumScreenMsg {
    data class AlbumInitialized(val albumId: Int) : AlbumScreenMsg
    data class NextPhotosLoaded(val photos: List<Photo>) : AlbumScreenMsg
    data object AllPhotosLoaded : AlbumScreenMsg
    data class PhotosLoadError(val statefulData: StatefulData<List<Photo>>) : AlbumScreenMsg
}

data class AlbumScreenState(
    val photos: StatefulData<List<Photo>> = LoadingData(),
    val currentPage: Int = 0,
    val currentAlbumId: Int?
)

interface AlbumScreenComponent {
    val store: AlbumScreenStore
}

class DefaultAlbumScreenComponent(
    val componentContext: ComponentContext,
    appContext: Context
) :
    AlbumScreenComponent, ComponentContext by componentContext {
    override val store: AlbumScreenStore = instanceKeeper.getStore {
        AlbumScreenStoreFactory(
            DefaultStoreFactory(),
            appContext,
            null
        ).create()
    }
}