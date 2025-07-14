package com.example.domain.repositories

import com.example.domain.api.AlbumApi
import com.example.domain.data.Album
import com.example.domain.db.daos.AlbumDao
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.testmocks.initAlbumPage
import com.example.testmocks.mockAlbumId
import com.example.testmocks.mockAlbums
import com.example.testmocks.mockPhotos
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AlbumsRepositoryTest {
    private lateinit var repository: AlbumsRepository
    private lateinit var api: AlbumApi
    private lateinit var dao: AlbumDao
    private lateinit var connectivityObserver: NetworkConnectivityObserver

    @Before
    fun setUp() {
        api = mock()
        dao = mock()
        connectivityObserver = mock()
        repository = AlbumsRepository(api, dao, connectivityObserver)
    }

    @Test
    fun `loadNextAlbums returns albums on success`() = runTest {
        whenever(api.getAlbums(any(), any())).thenReturn(mockAlbums)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadNextAlbums(initAlbumPage)
        assertEquals(mockAlbums, result)
    }

    @Test
    fun `loadNextAlbums returns null on exception`() = runTest {
        whenever(api.getAlbums(any(), any())).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadNextAlbums(initAlbumPage)
        assertNull(result)
    }

    @Test
    fun `loadNextAlbumPhotos returns photos on success`() = runTest {
        whenever(
            api.getPhotos(
                mockAlbumId,
                initAlbumPage * PHOTOS_PER_PAGE,
                PHOTOS_PER_PAGE
            )
        ).thenReturn(mockPhotos)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)
        whenever(dao.getAlbumById(mockAlbumId)).thenReturn(Album(mockAlbumId, ""))

        val result = repository.loadNextAlbumPhotos(mockAlbumId, initAlbumPage)
        assertEquals(mockPhotos, result)
    }

    @Test
    fun `loadNextAlbumPhotos returns null on exception`() = runTest {
        whenever(api.getPhotos(eq(mockAlbumId), any(), any())).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadNextAlbumPhotos(mockAlbumId, initAlbumPage)
        assertNull(result)
    }
}
