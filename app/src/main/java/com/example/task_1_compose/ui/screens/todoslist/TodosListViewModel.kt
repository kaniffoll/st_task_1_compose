package com.example.task_1_compose.ui.screens.todoslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.dataclasses.Todo
import com.example.domain.repositories.TodosRepository
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.errorName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodosListViewModel : ViewModel() {
    private val todosRepository = TodosRepository()

    private val _todos = MutableStateFlow<StatefulData<List<Todo>>>(LoadingData())
    val todos = _todos.asStateFlow()

    private var lastRemovedId: Int? = null
    private var lastUpdated: Pair<Int, String>? = null

    enum class Errors {
        LOADING_TODOS,
        ADD_TODO,
        REMOVE_TODO,
        UPDATE_TODO
    }

    private suspend fun retryAction() = when (todos.value.errorName()) {
        Errors.LOADING_TODOS -> loadTodos()
        Errors.ADD_TODO -> addTodo()
        Errors.REMOVE_TODO -> lastRemovedId?.let { removeTodoByIndex(it) }
        Errors.UPDATE_TODO -> lastUpdated?.let { updateText(it.first, it.second) }
        else -> {
            throw RuntimeException("UNKNOWN_ERROR")
        }
    }

    init {
        viewModelScope.launch {
            loadTodos()
        }
    }

    fun currentTodos(): List<Todo> {
        return _todos.value.unwrap(defaultValue = emptyList())
    }

    private suspend fun loadTodos() {
        when (val newPosts = todosRepository.loadTodos()) {
            null -> _todos.value = ErrorData(Errors.LOADING_TODOS)
            else -> _todos.value = SuccessData(newPosts)
        }
    }


    suspend fun addTodo() {
        when (val newTodo = todosRepository.createTodo(Todo(id = 0, text = ""))) {
            null -> _todos.value = ErrorData(Errors.ADD_TODO)
            else -> _todos.value = SuccessData(currentTodos() + newTodo)
        }
    }

    suspend fun removeTodoByIndex(id: Int) {
        lastRemovedId = id

        when (todosRepository.finishTodo(id)) {
            null -> _todos.value = ErrorData(Errors.REMOVE_TODO)
            else -> {
                val newList = currentTodos().toMutableList()
                newList.removeIf { it.id == id }
                _todos.value = SuccessData(newList)
            }
        }
    }

    suspend fun updateText(id: Int, text: String) {

        lastUpdated = id to text

        when (val newText = todosRepository.updateTodo(id, text)) {
            null -> _todos.value = ErrorData(Errors.UPDATE_TODO)
            else -> {
                val newTodos = currentTodos().map { todo ->
                    if (todo.id == id) todo.copy(text = newText) else todo
                }
                _todos.value = SuccessData(newTodos)
            }
        }

    }

    suspend fun retryLastFun() {
        retryAction()
    }
}