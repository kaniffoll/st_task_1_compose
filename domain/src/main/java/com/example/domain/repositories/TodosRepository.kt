package com.example.domain.repositories

import com.example.domain.apiinterfaces.TodoApi
import com.example.domain.data.dataclasses.Todo
import javax.inject.Inject

class TodosRepository @Inject constructor(private val api: TodoApi) {
    private val todos = mutableListOf<Todo>()
    private val activeTodos = mutableListOf<Todo>()
    private var newId = 0

    suspend fun loadTodos(): List<Todo>? {
        try {
            val response = api.getTodos()
            todos.addAll(response)
            activeTodos.addAll(todos.filter { !it.completed })
        } catch (e: Exception) {
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
            return null
        }
    }

    suspend fun createTodo(todo: Todo): Todo? {
        try {
            val response = api.createTodo(todo)
            val newTodo = response.copy(id = response.id + newId++)
            return newTodo
        } catch (e: Exception) {
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
            return null
        }
    }
}
