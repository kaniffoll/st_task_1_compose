package com.example.domain.di

import com.example.domain.apiinterfaces.AlbumApi
import com.example.domain.apiinterfaces.PostApi
import com.example.domain.apiinterfaces.TodoApi
import com.example.domain.apiinterfaces.UserApi
import com.example.domain.connectivityobserver.NetworkConnectivityObserver
import com.example.domain.db.daos.AlbumDao
import com.example.domain.db.daos.PostDao
import com.example.domain.db.daos.TodoDao
import com.example.domain.db.daos.UserDao
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
    fun providesPostsRepository(
        api: PostApi,
        dao: PostDao,
        connectivityObserver: NetworkConnectivityObserver,
    ) = PostsRepository(api, dao, connectivityObserver)

    @Provides
    fun providesUsersRepository(
        api: UserApi,
        dao: UserDao,
        connectivityObserver: NetworkConnectivityObserver,
    ) = UsersRepository(api, dao, connectivityObserver)

    @Provides
    fun providesAlbumsRepository(
        api: AlbumApi,
        dao: AlbumDao,
        connectivityObserver: NetworkConnectivityObserver
    ) = AlbumsRepository(api, dao, connectivityObserver)

    @Provides
    fun providesTodosRepository(
        api: TodoApi,
        dao: TodoDao,
        connectivityObserver: NetworkConnectivityObserver
    ) = TodosRepository(api, dao, connectivityObserver)
}