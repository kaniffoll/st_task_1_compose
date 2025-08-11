package com.example.domain.di

import com.example.domain.api.album.AlbumApi
import com.example.domain.api.album.AlbumApiImpl
import com.example.domain.api.post.PostApi
import com.example.domain.api.post.PostApiImpl
import com.example.domain.api.todo.TodoApi
import com.example.domain.api.todo.TodoApiImpl
import com.example.domain.api.user.UserApi
import com.example.domain.api.user.UserApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val apiModule = module {
    single {
        HttpClient(Android) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single<AlbumApi> {
        AlbumApiImpl(get())
    }

    single<PostApi> {
        PostApiImpl(get())
    }

    single<UserApi> {
        UserApiImpl(get())
    }

    single<TodoApi> {
        TodoApiImpl(get())
    }
}