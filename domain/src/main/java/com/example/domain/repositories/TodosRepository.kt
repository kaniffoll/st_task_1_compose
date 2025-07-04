package com.example.domain.repositories

import com.example.domain.apiinterfaces.TodoApi
import com.example.domain.connectivityobserver.NetworkConnectivityObserver
import com.example.domain.data.dataclasses.Todo
import com.example.domain.db.daos.TodoDao
import javax.inject.Inject

class TodosRepository @Inject constructor(
    private val api: TodoApi,
    private val dao: TodoDao,
    private val networkConnectivityObserver: NetworkConnectivityObserver,
) {
    private val todos = mutableListOf<Todo>()
    private var newId = 0

    suspend fun loadTodos(): List<Todo>? {
        val localTodos = dao.getAllTodos()
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val response = api.getTodos()
                val localMap = localTodos.associateBy { it.id }
                val remoteMap = response.associateBy { it.id }
                val merged = (remoteMap + localMap).values.toList()
                todos.clear()
                todos.addAll(merged.filter { !it.completed })
                dao.upsertAll(todos)
            } catch (e: Exception) {
                return null
            }
        } else {
            todos.clear()
            todos.addAll(localTodos)
        }
        return todos
    }

    suspend fun finishTodo(id: Int): Boolean? {
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val fieldsToUpdate = mapOf(
                    "completed" to true
                )
                api.completeTodo(id = id, fieldsToUpdate = fieldsToUpdate)
                dao.finishTodo(id)
                todos.removeIf { it.id == id }
            } catch (e: Exception) {
                return null
            }
        } else {
            dao.finishTodo(id)
            todos.removeIf { it.id == id }
        }
        return true
    }

    suspend fun createTodo(todo: Todo): Todo? {
        newId = (dao.getLastAddedId() ?: 0) + 1
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val response = api.createTodo(todo)
                val newTodo = response.copy(id = newId)
                dao.createTodo(newTodo)
                return newTodo
            } catch (e: Exception) {
                return null
            }
        } else {
            val newTodo = todo.copy(id = newId)
            dao.createTodo(newTodo)
            return newTodo
        }
    }

    suspend fun updateTodo(id: Int, localText: String): String? {
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val fieldsToUpdate = mapOf(
                    "title" to localText
                )
                api.updateTodo(id = id, fieldsToUpdate = fieldsToUpdate)
                dao.updateTodoText(id = id, localText = localText)
                todos.map { if (it.id == id) it.title = localText }
            } catch (e: Exception) {
                return null
            }
        } else {
            dao.updateTodoText(id = id, localText = localText)
            todos.map { if (it.id == id) it.title = localText }
        }
        return localText
    }
}
