package com.example.domain.repositories

import com.example.domain.api.PostApi
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.db.daos.PostDao
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.testmocks.mockPostComments
import com.example.testmocks.mockPosts
import com.example.testmocks.initPostsPage
import com.example.testmocks.mockPost
import com.example.testmocks.mockPostId
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PostsRepositoryTest {
    private lateinit var repository: PostsRepository
    private lateinit var api: PostApi
    private lateinit var dao: PostDao
    private lateinit var connectivityObserver: NetworkConnectivityObserver

    @Before
    fun setUp() {
        dao = mock()
        connectivityObserver = mock()
        api = mock()
        repository = PostsRepository(api, dao, connectivityObserver)
    }

    @Test
    fun `loadNextPosts returns posts on success`() = runTest {
        whenever(api.getPosts(any(), any())).thenReturn(mockPosts)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadNextPosts(initPostsPage)
        assertEquals(mockPosts, result)
    }

    @Test
    fun `loadNextPosts returns null on exception`() = runTest {
        whenever(api.getPosts(any(), any())).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadNextPosts(initPostsPage)
        assertNull(result)
    }

    @Test
    fun `loadPostCommentsById returns comments on success`() = runTest {
        whenever(
            api.getComments(
                mockPostId,
                initPostsPage * COMMENTS_PER_PAGE,
                COMMENTS_PER_PAGE
            )
        ).thenReturn(mockPostComments)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)
        whenever(dao.update(any())).thenReturn(Unit)
        whenever(dao.getPostById(mockPostId)).thenReturn(mockPost)

        val result = repository.loadPostCommentsById(mockPostId, initPostsPage)
        assertEquals(mockPostComments, result)
    }

    @Test
    fun `loadPostCommentsById returns null on exception`() = runTest {
        whenever(api.getComments(eq(mockPostId), any(), any())).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadPostCommentsById(mockPostId, initPostsPage)
        assertNull(result)
    }
}