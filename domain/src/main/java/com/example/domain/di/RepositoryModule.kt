package com.example.domain.di

import com.example.domain.apiinterfaces.AlbumApi
import com.example.domain.apiinterfaces.PostApi
import com.example.domain.apiinterfaces.TodoApi
import com.example.domain.apiinterfaces.UserApi
import com.example.domain.repositories.AlbumsRepository
import com.example.domain.repositories.PostsRepository
import com.example.domain.repositories.TodosRepository
import com.example.domain.repositories.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun providesPostsRepository(api: PostApi) = PostsRepository(api)

    @Provides
    fun providesUsersRepository(api: UserApi) = UsersRepository(api)

    @Provides
    fun providesAlbumsRepository(api: AlbumApi) = AlbumsRepository(api)

    @Provides
    fun providesTodosRepository(api: TodoApi) = TodosRepository(api)
}