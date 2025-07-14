package com.example.task_1_compose.fakeapi

import com.example.domain.api.TodoApi
import com.example.domain.data.Todo
import com.example.domain.api.RequestKeys.TITLE_KEY

class FakeTodoApi : TodoApi {
    private val todos = mutableListOf<Todo>()

    override suspend fun getTodos(): List<Todo> {
        return todos
    }

    override suspend fun createTodo(todo: Todo): Todo {
        todos.add(todo)
        return todo
    }

    override suspend fun updateTodo(id: Int, fieldsToUpdate: Map<String, String>) {
        val updatedTitle = fieldsToUpdate[TITLE_KEY]
        todos.find { it.id == id }?.title = updatedTitle ?: ""
    }

    override suspend fun completeTodo(id: Int, fieldsToUpdate: Map<String, Boolean>) {
        todos.find { it.id == id }?.completed = true
    }
}