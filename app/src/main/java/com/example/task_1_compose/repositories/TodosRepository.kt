package com.example.task_1_compose.repositories

import com.example.task_1_compose.data.dataclasses.Todo

class TodosRepository {
    private val todos: MutableList<Todo> = mutableListOf()

    fun loadTodos() : MutableList<Todo> {
        return todos
    }
}