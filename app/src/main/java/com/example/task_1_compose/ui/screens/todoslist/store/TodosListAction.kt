package com.example.task_1_compose.ui.screens.todoslist.store

sealed interface TodosListAction {
    data object LoadInitTodos: TodosListAction
}