package com.example.task_1_compose.repositories

class TodosRepository {
    private val todos: MutableList<String> = mutableListOf()

    fun loadTodos() : MutableList<String> {
        return todos
    }
}