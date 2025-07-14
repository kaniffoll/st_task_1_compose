package com.example.domain.repositories

import com.example.domain.api.TodoApi
import com.example.domain.db.daos.TodoDao
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.testmocks.mockTodo
import com.example.testmocks.mockTodoId
import com.example.testmocks.mockTodoText
import com.example.testmocks.mockTodos
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class TodosRepositoryTest {
    private lateinit var repository: TodosRepository
    private lateinit var api: TodoApi
    private lateinit var dao: TodoDao
    private lateinit var connectivityObserver: NetworkConnectivityObserver

    @Before
    fun setUp() {
        api = mock()
        dao = mock()
        connectivityObserver = mock()
        repository = TodosRepository(api, dao, connectivityObserver)
    }

    @Test
    fun `loadTodos returns todos on success`() = runTest {
        whenever(dao.getAllTodos()).thenReturn(emptyList())
        whenever(api.getTodos()).thenReturn(mockTodos)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)
        whenever(dao.upsertAll(any())).thenReturn(Unit)

        val result = repository.loadTodos()
        assertEquals(mockTodos, result)
    }

    @Test
    fun `loadTodos returns null on exception`() = runTest {
        whenever(api.getTodos()).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.loadTodos()
        assertNull(result)
    }

    @Test
    fun `finishTodo returns true on success`() = runTest {
        whenever(api.completeTodo(eq(mockTodoId), any())).thenReturn(Unit)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.finishTodo(mockTodoId)
        assertEquals(result, true)
    }

    @Test
    fun `finishTodo returns null on exception`() = runTest {
        whenever(api.completeTodo(eq(mockTodoId), any())).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.finishTodo(mockTodoId)
        assertNull(result)
    }

    @Test
    fun `createTodo returns todo on success`() = runTest {
        whenever(api.createTodo(mockTodo)).thenReturn(mockTodo)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)
        whenever(dao.getLastAddedId()).thenReturn(mockTodoId - 1)

        val result = repository.createTodo(mockTodo)
        assertEquals(result, mockTodo)
    }

    @Test
    fun `createTodo returns null on exception`() = runTest {
        whenever(api.createTodo(any())).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.createTodo(mockTodo)
        assertNull(result)
    }

    @Test
    fun `updateTodo returns string on success`() = runTest {
        val mockText = ""
        whenever(api.updateTodo(eq(mockTodoId), any())).thenReturn(Unit)
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.updateTodo(mockTodoId, mockText)
        assertEquals(result, mockText)
    }

    @Test
    fun `updateTodo returns null on exception`() = runTest {
        whenever(api.updateTodo(eq(mockTodoId), any())).thenThrow(RuntimeException())
        whenever(connectivityObserver.isNetworkAvailable()).thenReturn(true)

        val result = repository.updateTodo(mockTodoId, mockTodoText)
        assertNull(result)
    }
}