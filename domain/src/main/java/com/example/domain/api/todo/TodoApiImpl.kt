package com.example.domain.api.todo

import com.example.domain.data.Todo
import com.example.domain.resources.AppUrls.BASE_URL
import com.example.domain.resources.AppUrls.TODOS
import com.example.domain.utilities.e
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

class TodoApiImpl(private val client: HttpClient) : TodoApi {
    override suspend fun getTodos(): List<Todo>? {
        return try {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    appendPathSegments(TODOS)
                }
            }.body()
        } catch (e: Exception) {
            e.e()
            null
        }
    }

    override suspend fun createTodo(todo: Todo): Todo? {
        return try {
            client.post {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    appendPathSegments(TODOS)
                }
                contentType(ContentType.Application.Json)
                setBody(todo)
            }.body()
        } catch (e: Exception) {
            e.e()
            null
        }
    }

    override suspend fun updateTodo(
        id: Int,
        fieldsToUpdate: Map<String, String>,
    ) {
        try {
            client.patch {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    appendPathSegments(TODOS, "$id")
                }
                contentType(ContentType.Application.Json)
                setBody(fieldsToUpdate)
            }
        } catch (e: Exception) {
            e.e()
        }
    }

    override suspend fun completeTodo(
        id: Int,
        fieldsToUpdate: Map<String, Boolean>,
    ) {
        try {
            client.patch {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    appendPathSegments(TODOS, "$id")
                }
                contentType(ContentType.Application.Json)
                setBody(fieldsToUpdate)
            }
        } catch (e: Exception) {
            e.e()
        }
    }
}