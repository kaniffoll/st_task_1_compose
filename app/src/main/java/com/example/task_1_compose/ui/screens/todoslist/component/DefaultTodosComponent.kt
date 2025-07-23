package com.example.task_1_compose.ui.screens.todoslist.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.task_1_compose.ui.screens.todoslist.store.TodosListStoreFactory

class DefaultTodosComponent(
    componentContext: ComponentContext
) : TodosComponent, ComponentContext by componentContext {
    override val store = instanceKeeper.getStore { TodosListStoreFactory(DefaultStoreFactory()).create() }
}