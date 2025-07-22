package com.example.task_1_compose.ui.screens.todoslist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.Todo
import com.example.domain.interactors.WorkManagerInteractor
import com.example.domain.repositories.TodosRepository
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.errorName
import com.example.task_1_compose.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TodosListViewModel @Inject constructor(
    private val repository: TodosRepository,
    private val workManagerInteractor: WorkManagerInteractor
) : ViewModel() {

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

    suspend fun retryAction() = when (todos.value.errorName()) {
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
        when (val newTodos = repository.loadTodos()) {
            null -> _todos.value = ErrorData(Errors.LOADING_TODOS)
            else -> _todos.value = SuccessData(newTodos)
        }
    }


    suspend fun addTodo() {
        when (val newTodo = repository.createTodo(Todo(id = 0, title = ""))) {
            null -> _todos.value = ErrorData(Errors.ADD_TODO)
            else -> _todos.value = SuccessData(currentTodos() + newTodo)
        }
    }

    suspend fun removeTodoByIndex(id: Int) {
        lastRemovedId = id

        when (repository.finishTodo(id)) {
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

        when (val newText = repository.updateTodo(id, text)) {
            null -> _todos.value = ErrorData(Errors.UPDATE_TODO)
            else -> {
                val newTodos = currentTodos().map { todo ->
                    if (todo.id == id) todo.copy(title = newText) else todo
                }
                _todos.value = SuccessData(newTodos)
            }
        }
    }

    fun createWorkRequest(
        context: Context,
        triggerTime: LocalDateTime,
        title: String,
    ) {
        workManagerInteractor.createRemindWorkRequest(
            context = context,
            triggerTime = triggerTime,
            title = title,
            activityClass = MainActivity::class.java
        )
    }
}