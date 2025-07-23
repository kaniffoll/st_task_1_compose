package com.example.task_1_compose.ui.screens.todoslist.store

import com.example.domain.data.Todo
import com.example.domain.statefuldata.StatefulData

sealed interface TodosListMsg {
    data class ErrorMsg(val statefulData: StatefulData<List<Todo>>) : TodosListMsg
    data class TodosLoaded(val todos: List<Todo>) : TodosListMsg
    data class TodoAdded(val todos: List<Todo>) : TodosListMsg
    data class TodoRemoved(val todos: List<Todo>, val id: Int) : TodosListMsg
    data class TodoUpdated(val todos: List<Todo>, val id: Int, val text: String) : TodosListMsg
}