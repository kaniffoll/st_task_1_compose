package com.example.task_1_compose.screens.todoslist

import androidx.lifecycle.ViewModel
import com.example.task_1_compose.data.dataclasses.Todo
import com.example.task_1_compose.repositories.TodosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodosListViewModel : ViewModel() {
    private val _todosState = MutableStateFlow<List<Todo>>(mutableListOf())
    val todosState = _todosState.asStateFlow()
    private val todosRepository = TodosRepository()
    private var nextId = 0

    init {
        _todosState.value = todosRepository.loadTodos()
    }

    fun addTodo() {
        _todosState.value += Todo(id = nextId++, text = "")
    }

    fun removeTodoByIndex(id: Int) {
        val newList = _todosState.value.toMutableList()
        newList.removeIf{it.id == id}
        _todosState.value = newList
    }

    fun updateText(id: Int, text: String) {
        _todosState.value = _todosState.value.map { todo ->
            if (todo.id == id) todo.copy(text = text) else todo
        }
    }
}