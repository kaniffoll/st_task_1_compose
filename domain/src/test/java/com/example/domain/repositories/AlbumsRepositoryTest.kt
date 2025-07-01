package com.example.domain.repositories

import com.example.domain.apiinterfaces.AlbumApi
import com.example.domain.resources.AppSettings.PHOTOS_PER_PAGE
import com.example.domain.resources.mocks.mockAlbums
import com.example.domain.resources.mocks.mockPhotos
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

    private val initPage = 0
    private val mockAlbumId = 0

    private lateinit var repository: AlbumsRepository
    private lateinit var api: AlbumApi

    @Before
    fun setUp() {
        api = mock()
        repository = AlbumsRepository(api)
    }

    @Test
    fun `loadNextAlbums returns albums on success`() = runTest {
        whenever(api.getAlbums(any(), any())).thenReturn(mockAlbums)

        val result = repository.loadNextAlbums(initPage)
        assertEquals(mockAlbums, result)
    }

    @Test
    fun `loadNextAlbums returns null on exception`() = runTest {
        whenever(api.getAlbums(any(), any())).thenThrow(RuntimeException())

        val result = repository.loadNextAlbums(initPage)
        assertNull(result)
    }

    @Test
    fun `loadNextAlbumPhotos returns photos on success`() = runTest {
        whenever(
            api.getPhotos(
                mockAlbumId,
                initPage * PHOTOS_PER_PAGE,
                PHOTOS_PER_PAGE
            )
        ).thenReturn(mockPhotos)

        val result = repository.loadNextAlbumPhotos(mockAlbumId, initPage)
        assertEquals(mockPhotos, result)
    }

    @Test
    fun `loadNextAlbumPhotos returns null on exception`() = runTest {
        whenever(api.getPhotos(eq(mockAlbumId), any(), any())).thenThrow(RuntimeException())

        val result = repository.loadNextAlbumPhotos(mockAlbumId, initPage)
        assertNull(result)
    }
}
