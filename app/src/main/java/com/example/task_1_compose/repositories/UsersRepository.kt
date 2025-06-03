package com.example.task_1_compose.repositories

import com.example.task_1_compose.data.dataclasses.User
import com.example.task_1_compose.data.usersList

class UsersRepository {
    private val users = usersList

    fun getUsers(): List<User> {
        return users
    }
}