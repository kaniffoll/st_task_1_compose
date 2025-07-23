package com.example.task_1_compose.ui.screens.todoslist.store

import com.example.domain.data.Todo
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

data class TodosListState(
    val statefulData: StatefulData<List<Todo>> = LoadingData(),
    var lastRemovedId: Int? = null,
    val lastUpdated: Pair<Int, String>? = null,
    val currentTodos: List<Todo> = emptyList()
)
