package com.example.domain.di

import android.content.Context
import androidx.room.Room
import com.example.domain.db.AppDatabase
import com.example.domain.db.daos.AlbumDao
import com.example.domain.db.daos.PostDao
import com.example.domain.db.daos.TodoDao
import com.example.domain.db.daos.UserDao
import com.example.domain.resources.StringResources.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun providesPostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides
    @Singleton
    fun providesAlbumDao(db: AppDatabase): AlbumDao = db.albumDao()

    @Provides
    @Singleton
    fun providesTodoDao(db: AppDatabase): TodoDao = db.todoDao()
}