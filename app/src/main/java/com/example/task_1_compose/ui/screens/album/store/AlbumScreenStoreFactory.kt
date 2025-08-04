package com.example.task_1_compose.ui.screens.album.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.R
import com.example.domain.repositories.AlbumsRepository
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.resources.MviStoreNames.ALBUM_SCREEN_STORE_NAME
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import com.example.task_1_compose.ui.screens.album.store.AlbumScreenMsg.AlbumInitialized
import com.example.task_1_compose.ui.screens.album.store.AlbumScreenMsg.AllPhotosLoaded
import com.example.task_1_compose.ui.screens.album.store.AlbumScreenMsg.NextPhotosLoaded
import com.example.task_1_compose.ui.screens.album.store.AlbumScreenMsg.PhotosLoadError
import kotlinx.coroutines.launch

internal class AlbumScreenStoreFactory(
    private val repository:AlbumsRepository,
    private val storeFactory: StoreFactory,
    private val albumId: Int?
) {


    fun create(): AlbumScreenStore = object : AlbumScreenStore,
        Store<AlbumScreenIntent, AlbumScreenState, Nothing> by storeFactory.create(
            name = ALBUM_SCREEN_STORE_NAME,
            initialState = AlbumScreenState(currentAlbumId = albumId),
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
                            dispatch(AllPhotosLoaded)
                            return@launch
                        }

                        val currentAlbumId = state().currentAlbumId ?: run {
                            dispatch(
                                PhotosLoadError(
                                    ErrorData(
                                        ResourceProvider.getStringResource(R.string.loading_photos_error)
                                    )
                                )
                            )
                            return@launch
                        }

                        when (
                            val newPhotos = repository
                                .loadNextAlbumPhotos(currentAlbumId, state().currentPage)
                        ) {
                            null -> {
                                dispatch(
                                    PhotosLoadError(
                                        ErrorData(
                                            ResourceProvider.getStringResource(R.string.loading_photos_error)
                                        )
                                    )
                                )
                            }

                            else -> {
                                dispatch(
                                    NextPhotosLoaded(
                                        state().photos.unwrap(emptyList()) + newPhotos
                                    )
                                )
                            }
                        }
                    }
                }

                is AlbumScreenIntent.InitializeAlbumScreen -> {
                    dispatch(AlbumInitialized(intent.albumId))
                }
            }
        }

        private fun canLoadMorePhotos(): Boolean {
            return state().photos.canLoadMore(PHOTOS_PER_PAGE)
        }
    }

    private object ReducerImpl : Reducer<AlbumScreenState, AlbumScreenMsg> {
        override fun AlbumScreenState.reduce(msg: AlbumScreenMsg): AlbumScreenState = when (msg) {
            AllPhotosLoaded -> this
            is NextPhotosLoaded -> copy(
                photos = SuccessData(msg.photos),
                currentPage = currentPage + 1
            )

            is PhotosLoadError -> copy(
                photos = msg.errorDetails
            )

            is AlbumInitialized -> copy(
                photos = LoadingData(),
                currentPage = 0,
                currentAlbumId = msg.albumId
            )
        }
    }
}