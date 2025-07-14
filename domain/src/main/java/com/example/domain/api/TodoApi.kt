package com.example.domain.api

import com.example.domain.data.Todo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @POST("todos")
    suspend fun createTodo(@Body todo: Todo): Todo

    @PATCH("todos/{id}")
    suspend fun updateTodo(
        @Path("id") id: Int,
        @Body fieldsToUpdate: Map<String, String>
    )

    @PATCH("todos/{id}")
    suspend fun completeTodo(
        @Path("id") id: Int,
        @Body fieldsToUpdate: Map<String, Boolean>
    )

}