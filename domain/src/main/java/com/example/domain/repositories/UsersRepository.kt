package com.example.domain.repositories

import com.example.domain.apiinterfaces.UserApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.User
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.utilities.e
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: UserApi) {
    suspend fun loadUsers(): List<User>? {
        try {
            val response = api.getUsers()
            return response
        } catch (e: Exception) {
            e.e()
            return null
        }
    }

    suspend fun loadUserCommentsById(id: Int, currentPage: Int): List<Comment>? {
        try {
            val response = api.getComments(id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE)
            return response
        } catch (e: Exception) {
            e.e()
            return null
        }
    }
}