package com.example.task_1_compose.ui.screens.albumslist.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.repositories.AlbumsRepository
import com.example.domain.resources.AppSettings.ALBUMS_PER_PAGE
import com.example.domain.resources.MviStoreNames.ALBUMS_LIST_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

internal class AlbumsListStoreFactory(
    private val storeFactory: StoreFactory,
    private val context: Context
) {
    private val repository = AlbumsRepository()

    fun create(): AlbumsListStore = object : AlbumsListStore,
        Store<AlbumsListIntent, AlbumsListState, Nothing> by storeFactory.create(
            name = ALBUMS_LIST_STORE_NAME,
            initialState = AlbumsListState(),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<AlbumsListIntent, Nothing, AlbumsListState, AlbumsListMsg, Nothing>() {
        override fun executeIntent(intent: AlbumsListIntent) {
            when (intent) {
                is AlbumsListIntent.LoadNextAlbums -> {
                    scope.launch {
                        if (!canLoadMoreAlbums()) {
                            dispatch(AlbumsListMsg.AllAlbumsLoaded)
                            return@launch
                        }

                        when (val newAlbums = repository.loadNextAlbums(state().currentPage)) {
                            null -> dispatch(
                                AlbumsListMsg.AlbumsLoadError(
                                    ErrorData(ResourceProvider.getStringResource(R.string.loading_albums_error, context))
                                )
                            )
                            else -> {
                                dispatch(AlbumsListMsg.NextAlbumsLoaded(newAlbums))
                            }
                        }
                    }
                }
            }
        }

        private fun canLoadMoreAlbums(): Boolean {
            return state().albums.canLoadMore(ALBUMS_PER_PAGE)
        }
    }

    private object ReducerImpl : Reducer<AlbumsListState, AlbumsListMsg> {
        override fun AlbumsListState.reduce(msg: AlbumsListMsg): AlbumsListState = when (msg) {
            is AlbumsListMsg.AlbumsLoadError -> copy(albums = msg.statefulData)
            is AlbumsListMsg.AllAlbumsLoaded -> this
            is AlbumsListMsg.NextAlbumsLoaded -> copy(
                albums = SuccessData(
                    result = msg.albums
                ),
                currentPage = currentPage + 1
            )
        }
    }
}