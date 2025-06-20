package com.example.domain.repositories

import android.util.Log
import com.example.domain.RetrofitClient
import com.example.domain.data.dataclasses.Todo

class TodosRepository {
    private val api = RetrofitClient.todoApi
    private val todos = mutableListOf<Todo>()
    private val activeTodos = mutableListOf<Todo>()
    private var newId = 0

    suspend fun loadTodos(): List<Todo>? {
        try {
            val response = api.getTodos()
            todos.addAll(response)
            activeTodos.addAll(todos.filter { !it.completed })
        } catch (e: Exception) {
            Log.e("TODOS_REPOSITORY_FETCH_DATA", e.toString())
            return null
        }
        return activeTodos
    }

    suspend fun finishTodo(id: Int): Boolean? {
        try {
            val fieldsToUpdate = mapOf(
                "completed" to true
            )
            api.completeTodo(id = id, fieldsToUpdate = fieldsToUpdate)
            todos.map { if (it.id == id) it.completed = true }
            activeTodos.removeIf { it.id == id }
            return true
        } catch (e: Exception) {
            Log.e("TODOS_REPOSITORY_FINISH_TODO", e.toString())
            return null
        }
    }

    suspend fun createTodo(todo: Todo): Todo? {
        try {
            val response = api.createTodo(todo)
            val newTodo = response.copy(id = response.id + newId++)
            Log.d("TODOS_REPOSITORY_CURRENT_ID", newTodo.id.toString())
            return newTodo
        } catch (e: Exception) {
            Log.e("TODOS_REPOSITORY_CREATE_TODO", e.toString())
            return null
        }
    }

    suspend fun updateTodo(id: Int, localText: String): String? {
        try {
            val fieldsToUpdate = mapOf(
                "title" to localText
            )
            api.updateTodo(id = id, fieldsToUpdate = fieldsToUpdate)
            todos.map { if (it.id == id) it.title = localText }
            activeTodos.map { if (it.id == id) it.title = localText }
            return localText
        } catch (e: Exception) {
            Log.e("TODOS_REPOSITORY_UPDATE_TODO", e.toString())
            return null
        }
    }
}
