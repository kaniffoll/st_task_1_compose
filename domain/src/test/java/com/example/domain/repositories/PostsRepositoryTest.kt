package com.example.domain.repositories

import com.example.domain.apiinterfaces.PostApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
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
        val mockPosts = listOf(Post(id = 0, userId = 0, title = "", body = ""))
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
        val mockComments = listOf(Comment(name = "", body = ""))
        whenever(
            api.getComments(
                mockPostId,
                initPage * COMMENTS_PER_PAGE,
                COMMENTS_PER_PAGE
            )
        ).thenReturn(mockComments)

        val result = repository.loadPostCommentsById(mockPostId, initPage)
        assertEquals(mockComments, result)
    }

    @Test
    fun `loadPostCommentsById returns null on exception`() = runTest {
        whenever(api.getComments(eq(mockPostId), any(), any())).thenThrow(RuntimeException())

        val result = repository.loadPostCommentsById(mockPostId, initPage)
        assertNull(result)
    }
}