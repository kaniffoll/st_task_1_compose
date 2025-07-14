package com.example.domain.repositories

import com.example.domain.api.UserApi
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.db.daos.UserDao
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.testmocks.mockUserComments
import com.example.testmocks.mockUsers
import com.example.testmocks.initCommentsPage
import com.example.testmocks.mockUser
import com.example.testmocks.mockUserId
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
    private lateinit var repository: UsersRepository
    private lateinit var api: UserApi
    private lateinit var dao: UserDao
    private lateinit var connectivityObserver: NetworkConnectivityObserver

    @Before
    fun setUp() {
        api = mock()
        dao = mock()
        connectivityObserver = mock()
        repository = UsersRepository(api, dao, connectivityObserver)
    }

    @Test
    fun `loadNextUsers returns users on success`() = runTest {
        whenever(api.getUsers()).thenReturn(mockUsers)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadUsers()
        assertEquals(mockUsers, result)
    }

    @Test
    fun `loadNextUsers returns null on exception`() = runTest {
        whenever(api.getUsers()).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadUsers()
        assertNull(result)
    }

    @Test
    fun `loadUserCommentsById returns comments on success`() = runTest {
        whenever(
            api.getComments(
                mockUserId,
                initCommentsPage * COMMENTS_PER_PAGE,
                COMMENTS_PER_PAGE
            )
        ).thenReturn(mockUserComments)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)
        whenever(dao.update(any())).thenReturn(Unit)
        whenever(dao.getUserById(mockUserId)).thenReturn(mockUser)

        val result = repository.loadUserCommentsById(mockUserId, initCommentsPage)
        assertEquals(mockUserComments, result)
    }

    @Test
    fun `loadUserCommentsById returns null on exception`() = runTest {
        whenever(api.getComments(eq(mockUserId), any(), any())).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadUserCommentsById(mockUserId, initCommentsPage)
        assertNull(result)
    }
}