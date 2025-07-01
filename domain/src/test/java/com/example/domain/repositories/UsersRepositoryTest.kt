package com.example.domain.repositories

import com.example.domain.apiinterfaces.UserApi
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.mocks.mockUserComments
import com.example.domain.resources.mocks.mockUsers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UsersRepositoryTest {
    private val initPage = 0
    private val mockUserId = 0

    private lateinit var repository: UsersRepository
    private lateinit var api: UserApi

    @Before
    fun setUp() {
        api = mock()
        repository = UsersRepository(api)
    }

    @Test
    fun `loadNextUsers returns users on success`() = runTest {
        whenever(api.getUsers()).thenReturn(mockUsers)

        val result = repository.loadUsers()
        assertEquals(mockUsers, result)
    }

    @Test
    fun `loadNextUsers returns null on exception`() = runTest {
        whenever(api.getUsers()).thenThrow(RuntimeException())

        val result = repository.loadUsers()
        assertNull(result)
    }

    @Test
    fun `loadUserCommentsById returns comments on success`() = runTest {
        whenever(
            api.getComments(
                mockUserId,
                initPage * COMMENTS_PER_PAGE,
                COMMENTS_PER_PAGE
            )
        ).thenReturn(mockUserComments)

        val result = repository.loadUserCommentsById(mockUserId, initPage)
        assertEquals(mockUserComments, result)
    }

    @Test
    fun `loadUserCommentsById returns null on exception`() = runTest {
        whenever(api.getComments(eq(mockUserId), any(), any())).thenThrow(RuntimeException())

        val result = repository.loadUserCommentsById(mockUserId, initPage)
        assertNull(result)
    }
}