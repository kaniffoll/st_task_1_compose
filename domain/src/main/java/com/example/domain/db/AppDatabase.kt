package com.example.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.data.Album
import com.example.domain.data.Post
import com.example.domain.data.Todo
import com.example.domain.data.User
import com.example.domain.db.daos.AlbumDao
import com.example.domain.db.daos.PostDao
import com.example.domain.db.daos.TodoDao
import com.example.domain.db.daos.UserDao
import com.example.domain.resources.AppSettings.DATA_BASE_VERSION

@Database(
    entities = [User::class, Post::class, Album::class, Todo::class],
    version = DATA_BASE_VERSION
)
@TypeConverters(CustomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun albumDao(): AlbumDao
    abstract fun todoDao(): TodoDao
}