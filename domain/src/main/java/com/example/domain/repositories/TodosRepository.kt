package com.example.domain.repositories

import com.example.domain.api.RequestKeys.COMPLETED_KEY
import com.example.domain.api.RequestKeys.TITLE_KEY
import com.example.domain.api.todo.TodoApi
import com.example.domain.data.Todo

@Suppress("unused")
class TodosRepository(private val api: TodoApi) {
    private val todos = mutableListOf<Todo>()
    private var newId = 0

    suspend fun loadTodos(): List<Todo>? {
        val response = api.getTodos() ?: return null
        todos.addAll(response)
        return todos
    }

    suspend fun finishTodo(id: Int): Boolean? {
        try {
            val fieldsToUpdate = mapOf(
                COMPLETED_KEY to true
            )
            api.completeTodo(id = id, fieldsToUpdate = fieldsToUpdate)
            todos.removeIf { it.id == id }
        } catch (e: Exception) {
            return null
        }
        return true
    }

    suspend fun createTodo(): Todo? {
        val response = api.createTodo(Todo(0, "")) ?: return null
        return response.copy(id = response.id + newId++)
    }

    suspend fun updateTodo(id: Int, localText: String): String? {
        return try {
            val fieldsToUpdate = mapOf(
                TITLE_KEY to localText
            )
            api.updateTodo(id = id, fieldsToUpdate = fieldsToUpdate)
            todos.map { if (it.id == id) it.title = localText }
            localText
        } catch (e: Exception) {
            null
        }
    }
}

