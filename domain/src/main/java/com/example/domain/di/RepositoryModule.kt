package com.example.domain.di

import com.example.domain.repositories.AlbumsRepository
import com.example.domain.repositories.PostsRepository
import com.example.domain.repositories.TodosRepository
import com.example.domain.repositories.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<TodosRepository> {
        TodosRepository()
    }

    factory<PostsRepository> {
        PostsRepository()
    }

    factory<UsersRepository> {
        UsersRepository()
    }

    factory<AlbumsRepository> {
        AlbumsRepository(get())
    }
}