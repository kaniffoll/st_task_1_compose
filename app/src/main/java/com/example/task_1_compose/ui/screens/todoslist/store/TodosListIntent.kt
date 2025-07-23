package com.example.task_1_compose.ui.screens.todoslist.store

import android.content.Context
import java.time.LocalDateTime

sealed interface TodosListIntent {
    data object RetryLastAction : TodosListIntent
    data object LoadTodos : TodosListIntent
    data object AddTodo : TodosListIntent
    data class RemoveTodoByIndex(val id: Int) : TodosListIntent
    data class UpdateTodoText(val id: Int, val text: String) : TodosListIntent
    data class CreateWorkRequest(
        val context: Context,
        val triggerTime: LocalDateTime,
        val title: String
    ) : TodosListIntent
}