package com.example.task_1_compose.fakeapi

import com.example.domain.api.UserApi
import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.testmocks.mockUserComments

class FakeUserApi : UserApi {
    private val users = listOf<User>()

    override suspend fun getUsers(): List<User> {
        return users
    }

    override suspend fun getComments(userId: Int, start: Int, limit: Int): List<Comment> {
        return mockUserComments.subList(start, start + limit)
    }

}