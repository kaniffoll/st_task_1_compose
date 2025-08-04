package com.example.task_1_compose.ui.screens.todoslist.store

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.data.Todo
import com.example.domain.repositories.TodosRepository
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import java.time.LocalDateTime

interface TodosListStore : Store<TodosListIntent, TodosListState, Nothing>

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

data class TodosListState(
    val todos: StatefulData<List<Todo>> = LoadingData(),
    var lastRemovedId: Int? = null,
    val lastUpdated: Pair<Int, String>? = null,
)

sealed interface TodosListMsg {
    data class ErrorMsg(val errorDetails: ErrorData<List<Todo>>) : TodosListMsg
    data class TodosLoaded(val todos: List<Todo>) : TodosListMsg
    data class TodoAdded(val todos: List<Todo>) : TodosListMsg
    data class TodoRemoved(val todos: List<Todo>, val id: Int) : TodosListMsg
    data class TodoUpdated(val todos: List<Todo>, val id: Int, val text: String) : TodosListMsg
}

enum class TodosListErrors {
    LOADING_TODOS,
    ADD_TODO,
    REMOVE_TODO,
    UPDATE_TODO
}

class DefaultTodosComponent(
    componentContext: ComponentContext,
    repository: TodosRepository
) : TodosComponent, ComponentContext by componentContext {
    override val store =
        instanceKeeper.getStore {
            TodosListStoreFactory(
                repository,
                DefaultStoreFactory()
            ).create()
        }
}

interface TodosComponent {
    val store: TodosListStore
}