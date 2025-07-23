package com.example.task_1_compose.ui.screens.todoslist.store

import android.content.Context
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.domain.interactors.WorkManagerInteractor
import com.example.domain.repositories.TodosRepository
import com.example.domain.resources.StringResources.TODOS_LIST_STORE_NAME
import com.example.domain.resources.StringResources.UNKNOWN_ERROR
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.errorName
import com.example.task_1_compose.MainActivity
import kotlinx.coroutines.launch
import java.time.LocalDateTime

internal class TodosListStoreFactory(
    private val storeFactory: StoreFactory
) {
    private val repository = TodosRepository()
    private val workManagerInteractor = WorkManagerInteractor()

    fun create(): TodosListStore = object : TodosListStore,
        Store<TodosListIntent, TodosListState, Nothing> by storeFactory.create(
            name = TODOS_LIST_STORE_NAME,
            initialState = TodosListState(),
            bootstrapper = SimpleBootstrapper(TodosListAction.LoadInitTodos),
            reducer = ReducerImpl,
            executorFactory = { ExecutorImpl() }
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<TodosListIntent, TodosListAction, TodosListState, TodosListMsg, Nothing>() {
        override fun executeIntent(intent: TodosListIntent) {
            when (intent) {
                is TodosListIntent.AddTodo -> addTodo()
                is TodosListIntent.CreateWorkRequest -> createWorkRequest(
                    intent.context,
                    intent.triggerTime,
                    intent.title
                )

                is TodosListIntent.LoadTodos -> loadTodos()
                is TodosListIntent.RemoveTodoByIndex -> removeTodoByIndex(intent.id)
                is TodosListIntent.RetryLastAction -> retryLastAction()
                is TodosListIntent.UpdateTodoText -> updateTodoText(intent.id, intent.text)
            }
        }

        override fun executeAction(action: TodosListAction) {
            when (action) {
                is TodosListAction.LoadInitTodos -> loadTodos()
            }
        }

        fun createWorkRequest(
            context: Context,
            triggerTime: LocalDateTime,
            title: String
        ) {
            workManagerInteractor.createRemindWorkRequest(
                context = context,
                triggerTime = triggerTime,
                title = title,
                activityClass = MainActivity::class.java
            )
        }

        fun retryLastAction() {
            when (state().statefulData.errorName()) {
                TodosListErrors.LOADING_TODOS -> loadTodos()
                TodosListErrors.ADD_TODO -> addTodo()
                TodosListErrors.REMOVE_TODO -> state().lastRemovedId?.let { removeTodoByIndex(it) }
                TodosListErrors.UPDATE_TODO -> state().lastUpdated?.let {
                    updateTodoText(
                        it.first,
                        it.second
                    )
                }

                else -> {
                    throw RuntimeException(UNKNOWN_ERROR)
                }
            }
        }

        fun updateTodoText(id: Int, text: String) {
            scope.launch {
                when (val newText = repository.updateTodo(id, text)) {
                    null -> dispatch(TodosListMsg.ErrorMsg(ErrorData(TodosListErrors.UPDATE_TODO)))
                    else -> dispatch(
                        TodosListMsg.TodoUpdated(
                            todos = state().currentTodos.map { todo ->
                                if (todo.id == id) todo.copy(title = newText) else todo
                            },
                            id = id,
                            text = newText
                        )
                    )
                }
            }
        }

        fun removeTodoByIndex(id: Int) {
            scope.launch {
                when (repository.finishTodo(id)) {
                    null -> dispatch(TodosListMsg.ErrorMsg(ErrorData(TodosListErrors.REMOVE_TODO)))
                    else -> dispatch(
                        TodosListMsg.TodoRemoved(
                            todos = state().currentTodos.toMutableList().apply {
                                removeIf { it.id == id }
                            },
                            id = id
                        )
                    )
                }
            }
        }

        fun addTodo() {
            scope.launch {
                when (val newTodo = repository.createTodo()) {
                    null -> dispatch(TodosListMsg.ErrorMsg(ErrorData(TodosListErrors.ADD_TODO)))
                    else -> {
                        if (state().currentTodos.any { it.id == newTodo.id}){
                            return@launch
                        }
                        dispatch(TodosListMsg.TodoAdded(state().currentTodos + newTodo))
                    }
                }
            }
        }

        fun loadTodos() {
            scope.launch {
                when (val newTodos = repository.loadTodos()) {
                    null -> dispatch(TodosListMsg.ErrorMsg(ErrorData(TodosListErrors.LOADING_TODOS)))
                    else -> dispatch(TodosListMsg.TodosLoaded(newTodos))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<TodosListState, TodosListMsg> {
        override fun TodosListState.reduce(msg: TodosListMsg): TodosListState = when (msg) {
            is TodosListMsg.ErrorMsg -> copy(statefulData = msg.statefulData)

            is TodosListMsg.TodoAdded -> {
                copy(
                    statefulData = SuccessData(msg.todos),
                    currentTodos = msg.todos
                )
            }

            is TodosListMsg.TodoRemoved -> copy(
                statefulData = SuccessData(msg.todos),
                currentTodos = msg.todos,
                lastRemovedId = msg.id
            )

            is TodosListMsg.TodoUpdated -> copy(
                statefulData = SuccessData(msg.todos),
                currentTodos = msg.todos,
                lastUpdated = msg.id to msg.text
            )

            is TodosListMsg.TodosLoaded -> copy(
                statefulData = SuccessData(msg.todos),
                currentTodos = msg.todos
            )
        }
    }
}