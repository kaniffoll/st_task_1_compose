package com.example.domain.repositories

import com.example.domain.apiinterfaces.TodoApi
import com.example.domain.resources.mocks.mockTodo
import com.example.domain.resources.mocks.mockTodos
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

    private val mockTodoId = 0

    private lateinit var repository: TodosRepository
    private lateinit var api: TodoApi

    @Before
    fun setUp() {
        api = mock()
        repository = TodosRepository(api)
    }

    @Test
    fun `loadTodos returns todos on success`() = runTest {
        whenever(api.getTodos()).thenReturn(mockTodos)

        val result = repository.loadTodos()
        assertEquals(mockTodos, result)
    }

    @Test
    fun `loadTodos returns null on exception`() = runTest {
        whenever(api.getTodos()).thenThrow(RuntimeException())

        val result = repository.loadTodos()
        assertNull(result)
    }

    @Test
    fun `finishTodo returns true on success`() = runTest {
        whenever(api.completeTodo(eq(mockTodoId), any())).thenReturn(Unit)

        val result = repository.finishTodo(mockTodoId)
        assertEquals(result, true)
    }

    @Test
    fun `finishTodo returns null on exception`() = runTest {
        whenever(api.completeTodo(eq(mockTodoId), any())).thenThrow(RuntimeException())

        val result = repository.finishTodo(mockTodoId)
        assertNull(result)
    }

    @Test
    fun `createTodo returns todo on success`() = runTest {

        whenever(api.createTodo(mockTodo)).thenReturn(mockTodo)

        val result = repository.createTodo(mockTodo)
        assertEquals(result, mockTodo)
    }

    @Test
    fun `createTodo returns null on exception`() = runTest {
        whenever(api.createTodo(any())).thenThrow(RuntimeException())

        val result = repository.createTodo(mockTodo)
        assertNull(result)
    }

    @Test
    fun `updateTodo returns string on success`() = runTest {
        val mockText = ""
        whenever(api.updateTodo(eq(mockTodoId), any())).thenReturn(Unit)

        val result = repository.updateTodo(mockTodoId, mockText)
        assertEquals(result, mockText)
    }

    @Test
    fun `updateTodo returns null on exception`() = runTest {
        whenever(api.updateTodo(eq(mockTodoId), any())).thenThrow(RuntimeException())

        val result = repository.updateTodo(mockTodoId, any())
        assertNull(result)
    }
}