package com.example.task_1_compose.ui.screens.album.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.repositories.AlbumsRepository
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.resources.StringResources.ALBUM_SCREEN_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import kotlinx.coroutines.launch

//TODO: нужно дописать редьюсер
internal class AlbumScreenStoreFactory(
    private val storeFactory: StoreFactory,
    context: Context,
    private val albumId: Int
) {
    private val resourceProvider = ResourceProvider(context)
    private val repository = AlbumsRepository()

    fun create(): AlbumScreenStore = object : AlbumScreenStore,
        Store<AlbumScreenIntent, AlbumScreenState, Nothing> by storeFactory.create(
            name = ALBUM_SCREEN_STORE_NAME,
            initialState = AlbumScreenState(),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<AlbumScreenIntent, Nothing, AlbumScreenState, AlbumScreenMsg, Nothing>() {
        override fun executeIntent(intent: AlbumScreenIntent) {
            when (intent) {
                is AlbumScreenIntent.LoadNextPhotos -> {
                    scope.launch {
                        if (!canLoadMorePhotos()) {
                            dispatch(AlbumScreenMsg.AllPhotosLoaded)
                            return@launch
                        }

                        when (
                            val newPhotos = repository
                                .loadNextAlbumPhotos(albumId, state().currentPage)
                        ) {
                            null -> {
                                dispatch(
                                    AlbumScreenMsg.PhotosLoadError(
                                        ErrorData(
                                            resourceProvider.getStringResource(
                                                R.string.loading_photos_error
                                            )
                                        )
                                    )
                                )
                            }

                            else -> {
                                dispatch(
                                    AlbumScreenMsg.NextPhotosLoaded(
                                        state().currentPhotos + newPhotos
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        private fun canLoadMorePhotos(): Boolean {
            return state().statefulData.canLoadMore(PHOTOS_PER_PAGE)
        }
    }

    private object ReducerImpl : Reducer<AlbumScreenState, AlbumScreenMsg> {
        override fun AlbumScreenState.reduce(msg: AlbumScreenMsg): AlbumScreenState = when(msg) {
            AlbumScreenMsg.AllPhotosLoaded -> this
            is AlbumScreenMsg.NextPhotosLoaded -> TODO()
            is AlbumScreenMsg.PhotosLoadError -> TODO()
        }
    }
}