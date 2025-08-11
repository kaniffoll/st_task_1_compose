package com.example.domain.api.todo

import com.example.domain.data.Todo

interface TodoApi {
    suspend fun getTodos(): List<Todo>?
    suspend fun createTodo(todo: Todo): Todo?
    suspend fun updateTodo(id: Int, fieldsToUpdate: Map<String, String>)
    suspend fun completeTodo(id: Int, fieldsToUpdate: Map<String, Boolean>)
}