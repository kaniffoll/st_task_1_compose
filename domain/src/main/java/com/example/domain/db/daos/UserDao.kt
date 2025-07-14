package com.example.domain.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.domain.data.User
import com.example.domain.db.daos.base.BaseDao

@Dao
interface UserDao : BaseDao<User> {
    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User
}