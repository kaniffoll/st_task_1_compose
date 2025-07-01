package com.example.domain.repositories

import com.example.domain.apiinterfaces.PostApi
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.mocks.mockPostComments
import com.example.domain.resources.mocks.mockPosts
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

    private val initPage = 0
    private val mockPostId = 0

    private lateinit var repository: PostsRepository
    private lateinit var api: PostApi

    @Before
    fun setUp() {
        api = mock()
        repository = PostsRepository(api)
    }

    @Test
    fun `loadNextPosts returns posts on success`() = runTest {

        whenever(api.getPosts(any(), any())).thenReturn(mockPosts)

        val result = repository.loadNextPosts(initPage)
        assertEquals(mockPosts, result)
    }

    @Test
    fun `loadNextPosts returns null on exception`() = runTest {
        whenever(api.getPosts(any(), any())).thenThrow(RuntimeException())

        val result = repository.loadNextPosts(initPage)
        assertNull(result)
    }

    @Test
    fun `loadPostCommentsById returns comments on success`() = runTest {
        whenever(
            api.getComments(
                mockPostId,
                initPage * COMMENTS_PER_PAGE,
                COMMENTS_PER_PAGE
            )
        ).thenReturn(mockPostComments)

        val result = repository.loadPostCommentsById(mockPostId, initPage)
        assertEquals(mockPostComments, result)
    }

    @Test
    fun `loadPostCommentsById returns null on exception`() = runTest {
        whenever(api.getComments(eq(mockPostId), any(), any())).thenThrow(RuntimeException())

        val result = repository.loadPostCommentsById(mockPostId, initPage)
        assertNull(result)
    }
}