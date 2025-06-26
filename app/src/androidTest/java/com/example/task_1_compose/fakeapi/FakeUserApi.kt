package com.example.task_1_compose.fakeapi

import com.example.domain.apiinterfaces.UserApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.User

class FakeUserApi : UserApi {
    private val users = listOf<User>()

    override suspend fun getUsers(): List<User> {
        return users
    }

    override suspend fun getComments(userId: Int, start: Int, limit: Int): List<Comment> {
        return users[userId].comments
    }

}