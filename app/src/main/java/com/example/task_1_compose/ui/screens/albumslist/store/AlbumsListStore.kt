package com.example.task_1_compose.ui.screens.albumslist.store

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.data.Album
import com.example.domain.repositories.AlbumsRepository
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

interface AlbumsListStore : Store<AlbumsListIntent, AlbumsListState, Nothing>

sealed interface AlbumsListIntent {
    data object LoadNextAlbums : AlbumsListIntent
}

sealed interface AlbumsListMsg {
    data class NextAlbumsLoaded(val albums: List<Album>) : AlbumsListMsg
    data object AllAlbumsLoaded : AlbumsListMsg
    data class AlbumsLoadError(val errorDetails: ErrorData<List<Album>>) : AlbumsListMsg
}

data class AlbumsListState(
    val albums: StatefulData<List<Album>> = LoadingData(),
    val currentPage: Int = 0
)

interface AlbumsListComponent {
    val store: AlbumsListStore
}

class DefaultAlbumsListComponent(
    componentContext: ComponentContext,
    repository: AlbumsRepository
) :
    AlbumsListComponent, ComponentContext by componentContext {
    override val store: AlbumsListStore = instanceKeeper.getStore {
        AlbumsListStoreFactory(
            repository,
            DefaultStoreFactory()
        ).create()
    }
}