package com.example.domain.repositories

import com.example.domain.api.RequestKeys.COMPLETED_KEY
import com.example.domain.api.RequestKeys.TITLE_KEY
import com.example.domain.api.todo.TodoApi
import com.example.domain.data.Todo
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.utilities.db.createTodo
import com.example.domain.utilities.db.finishTodo
import com.example.domain.utilities.db.getLastAddedTodoId
import com.example.domain.utilities.db.getTodos
import com.example.domain.utilities.db.saveTodos
import com.example.domain.utilities.db.updateTodoText
import io.realm.kotlin.Realm

@Suppress("unused")
class TodosRepository(
    private val api: TodoApi,
    private val realm: Realm,
    private val connectivityObserver: NetworkConnectivityObserver,
) {
    private val todos = mutableListOf<Todo>()
    private var newId = 0

    suspend fun loadTodos(): List<Todo>? {
        val localTodos = realm.getTodos()
        if (connectivityObserver.isNetworkAvailable()) {
            val response = api.getTodos() ?: return null
            val localMap = localTodos.associateBy { it.id }
            val remoteMap = response.associateBy { it.id }
            val merged = (remoteMap + localMap).values.toList()
            todos.clear()
            todos.addAll(merged.filter { !it.completed })
            realm.saveTodos(todos)
        } else {
            todos.clear()
            todos.addAll(localTodos)
        }
        return todos
    }

    suspend fun finishTodo(id: Int): Boolean? {
        if (connectivityObserver.isNetworkAvailable()) {
            try {
                val fieldsToUpdate = mapOf(
                    COMPLETED_KEY to true
                )
                api.completeTodo(id = id, fieldsToUpdate = fieldsToUpdate)
                realm.finishTodo(id)
            } catch (e: Exception) {
                return null
            }
        } else {
            realm.finishTodo(id)
        }
        todos.removeIf { it.id == id }
        return true
    }

    suspend fun createTodo(): Todo? {
        newId = realm.getLastAddedTodoId()
        if (connectivityObserver.isNetworkAvailable()) {
            val response = api.createTodo(Todo(0, "")) ?: return null
            val newTodo = response.copy(id = newId)
            realm.createTodo(newTodo)
            return newTodo
        } else {
            val newTodo = Todo(newId)
            realm.createTodo(newTodo)
            return newTodo
        }
    }

    suspend fun updateTodo(id: Int, localText: String): String? {
        if (connectivityObserver.isNetworkAvailable()) {
            try {
                val fieldsToUpdate = mapOf(
                    TITLE_KEY to localText
                )
                api.updateTodo(id = id, fieldsToUpdate = fieldsToUpdate)
                realm.updateTodoText(id, localText)
            } catch (e: Exception) {
                return null
            }
        } else {
            realm.updateTodoText(id, localText)
        }
        todos.forEach { if (it.id == id) it.title = localText }
        return localText
    }
}

