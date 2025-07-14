package com.example.domain.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.domain.data.Post
import com.example.domain.db.daos.base.BaseDao

@Dao
interface PostDao: BaseDao<Post> {
    @Query("SELECT * FROM Post")
    suspend fun getAllPosts(): List<Post>

    @Query("SELECT * FROM Post WHERE id = :id")
    suspend fun getPostById(id: Int): Post
}