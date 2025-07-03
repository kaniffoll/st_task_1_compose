package com.example.domain.db.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.domain.data.dataclasses.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM Post")
    suspend fun getLoadedPosts(): List<Post>

    @Query("SELECT * FROM Post WHERE id = :id")
    suspend fun getPostById(id: Int): Post

    @Upsert
    suspend fun upsertPosts(posts: List<Post>)

    @Update
    suspend fun updatePost(post: Post)
}