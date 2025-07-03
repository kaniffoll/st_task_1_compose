package com.example.domain.db.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.domain.data.dataclasses.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Upsert
    suspend fun upsertUsers(users: List<User>): User

    @Update
    suspend fun updateUser(user: User)
}