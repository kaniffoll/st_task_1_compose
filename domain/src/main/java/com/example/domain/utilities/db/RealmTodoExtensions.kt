package com.example.domain.utilities.db

import com.example.domain.data.Todo
import com.example.domain.data.realm.TodoRealm
import com.example.domain.utilities.toTodo
import com.example.domain.utilities.toTodoRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.query.Sort

suspend fun Realm.createTodo(newTodo: Todo) = this.write {
    copyToRealm(
        newTodo.toTodoRealm(),
    )
}

fun Realm.getLastAddedTodoId() =
    (this.query(TodoRealm::class).sort("id", Sort.DESCENDING).first().find()?.id ?: 0) + 1

suspend fun Realm.finishTodo(id: Int) = this.write {
    val todo = this.query(TodoRealm::class, "id == $0", id)
        .first()
        .find() ?: return@write null
    todo.completed = true
}

suspend fun Realm.updateTodoText(id: Int, localText: String) = this.write {
    val todo = this.query(TodoRealm::class, "id == $0", id)
        .first()
        .find() ?: return@write
    todo.title = localText
}

suspend fun Realm.saveTodos(todos: MutableList<Todo>) = this.write {
    todos.forEach {
        copyToRealm(
            instance = it.toTodoRealm(),
            updatePolicy = UpdatePolicy.ALL
        )
    }
}

fun Realm.getTodos() = this.query(TodoRealm::class).find().toList().map { it.toTodo() }