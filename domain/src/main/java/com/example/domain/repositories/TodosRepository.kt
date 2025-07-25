package com.example.domain.repositories

import com.example.domain.data.Todo
import com.example.domain.utilities.d
import kotlinx.coroutines.delay

@Suppress("unused")
class TodosRepository {
    private val todos = mutableListOf<Todo>()
    private var newId = 0

    suspend fun loadTodos(): List<Todo>? {
        "CALL".d("AAA")
        delay(1000L)
        return try {
            todos.toList()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun finishTodo(id: Int): Boolean? {
        delay(100L)
        try {
            todos.removeIf { it.id == id }
        } catch (e: Exception) {
            return null
        }
        return true
    }

    suspend fun createTodo(): Todo? {
        delay(100L)
        return try {
            val todo = Todo(newId++, "")
            todos.add(todo)
            todo
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateTodo(id: Int, localText: String): String? {
        delay(100L)
        try {
            todos.map {
                if (it.id == id) it.title = localText
            }
            return localText
        } catch (e: Exception) {
            return null
        }
    }
}

