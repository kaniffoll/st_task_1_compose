package com.example.domain.api.user

import com.example.domain.data.Comment
import com.example.domain.data.User

interface UserApi {
    suspend fun getUsers(): List<User>?
    suspend fun getComments(userId: Int, start: Int, limit: Int): List<Comment>?
}