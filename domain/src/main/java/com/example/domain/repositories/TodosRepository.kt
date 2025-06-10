package com.example.domain.repositories

import com.example.domain.data.dataclasses.Todo

class TodosRepository {
    private val todos: MutableList<Todo> = mutableListOf()

    fun loadTodos(): MutableList<Todo> {
        return todos
    }
}