package com.example.domain.repositories

import com.example.domain.data.dataclasses.User
import com.example.domain.data.usersList

class UsersRepository {
    private val users = usersList

    fun getUsers(): List<User> {
        return users
    }
}