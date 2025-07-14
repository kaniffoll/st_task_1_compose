package com.example.task_1_compose.di

import com.example.domain.api.AlbumApi
import com.example.domain.api.PostApi
import com.example.domain.api.TodoApi
import com.example.domain.api.UserApi
import com.example.domain.di.ApiModule
import com.example.task_1_compose.fakeapi.FakeAlbumApi
import com.example.task_1_compose.fakeapi.FakePostApi
import com.example.task_1_compose.fakeapi.FakeTodoApi
import com.example.task_1_compose.fakeapi.FakeUserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class], replaces = [ApiModule::class]
)
object TestApiModule {

    @Provides
    @Singleton
    fun providePostApi(): PostApi = FakePostApi()

    @Provides
    @Singleton
    fun provideAlbumApi(): AlbumApi = FakeAlbumApi()

    @Provides
    @Singleton
    fun provideUserApi(): UserApi = FakeUserApi()

    @Provides
    @Singleton
    fun provideTodoApi(): TodoApi = FakeTodoApi()
}