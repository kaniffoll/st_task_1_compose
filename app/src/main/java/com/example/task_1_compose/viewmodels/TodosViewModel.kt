package com.example.task_1_compose.viewmodels

import androidx.lifecycle.ViewModel
import com.example.task_1_compose.repositories.TodosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodosViewModel(todosRepository: TodosRepository) : ViewModel() {
    private val _todosState = MutableStateFlow<List<String>>(mutableListOf())
    val todosState = _todosState.asStateFlow()

    init {
        _todosState.value = todosRepository.loadTodos()
    }

    fun addTodo() {
        _todosState.value += ""
    }

    fun removeTodoByIndex(index: Int) {
        val newList = _todosState.value.toMutableList()
        newList.removeAt(index)
        _todosState.value = newList
    }

    fun updateText(index: Int, text: String) {
        val newList = _todosState.value.toMutableList()
        newList[index] = text
        _todosState.value = newList
    }
}