package com.example.domain.repositories

import android.util.Log
import com.example.domain.RetrofitClient
import com.example.domain.apiinterfaces.UserApi
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.User
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE

class UsersRepository {
    private val api: UserApi = RetrofitClient.userApi

    suspend fun loadUsers(): List<User>? {
        try {
            val response = api.getUsers()
            return response
        } catch (e: Exception) {
            Log.e("USERS_REPOSITORY", e.toString())
            return null
        }
    }

    suspend fun loadUserCommentsById(id: Int, currentPage: Int): List<Comment>? {
        try {
            val response = api.getComments(id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE)
            return response
        } catch (e: Exception) {
            Log.e("USERS_REPOSITORY", e.toString())
            return null
        }
    }
}