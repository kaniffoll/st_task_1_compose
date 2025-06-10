package com.example.task_1_compose.ui.screens.todoslist

import androidx.lifecycle.ViewModel
import com.example.domain.data.dataclasses.Todo
import com.example.domain.repositories.TodosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodosListViewModel : ViewModel() {
    private val todosRepository = TodosRepository()

    private val _todos = MutableStateFlow<List<Todo>>(mutableListOf())
    val todos = _todos.asStateFlow()

    private var nextId = 0

    init {
        _todos.value = todosRepository.loadTodos()
    }

    fun addTodo() {
        _todos.value += Todo(id = nextId++, text = "")
    }

    fun removeTodoByIndex(id: Int) {
        val newList = _todos.value.toMutableList()
        newList.removeIf { it.id == id }
        _todos.value = newList
    }

    fun updateText(id: Int, text: String) {
        _todos.value = _todos.value.map { todo ->
            if (todo.id == id) todo.copy(text = text) else todo
        }
    }
}