package com.example.domain.repositories

import com.example.domain.api.UserApi
import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.db.daos.UserDao
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.utilities.NetworkConnectivityObserver
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val api: UserApi,
    private val dao: UserDao,
    private val networkConnectivityObserver: NetworkConnectivityObserver,
) {
    suspend fun loadUsers(): List<User>? {
        if (!networkConnectivityObserver.isNetworkAvailable()) {
            return dao.getAllUsers()
        }

        try {
            val response = api.getUsers()
            dao.upsertAll(response)
            return response
        } catch (e: Exception) {
            return null
        }
    }

    suspend fun loadUserCommentsById(id: Int, currentPage: Int): List<Comment>? {
        if (!networkConnectivityObserver.isNetworkAvailable()) {
            return emptyList()
        }

        try {
            val response =
                api.getComments(id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE)
            val currentUser = dao.getUserById(id)
            dao.update(currentUser.copy(comments = response.toMutableList()))
            return response
        } catch (e: Exception) {
            return null
        }
    }
}