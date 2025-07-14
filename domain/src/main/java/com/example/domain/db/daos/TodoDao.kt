package com.example.domain.db.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.domain.data.Todo
import com.example.domain.db.daos.base.BaseDao

@Dao
interface TodoDao : BaseDao<Todo> {
    @Query("SELECT * FROM Todo")
    suspend fun getAllTodos(): List<Todo>

    @Query("SELECT MAX(id) FROM Todo")
    suspend fun getLastAddedId(): Int?

    @Query("DELETE FROM Todo WHERE id = :id")
    suspend fun finishTodo(id: Int)

    @Upsert
    suspend fun createTodo(todo: Todo)

    @Query("UPDATE Todo SET title = :localText WHERE id = :id")
    suspend fun updateTodoText(id: Int, localText: String)
}