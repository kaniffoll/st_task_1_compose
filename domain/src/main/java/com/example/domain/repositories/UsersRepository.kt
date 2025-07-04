package com.example.domain.repositories

import com.example.domain.apiinterfaces.UserApi
import com.example.domain.connectivityobserver.NetworkConnectivityObserver
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.User
import com.example.domain.db.daos.UserDao
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val api: UserApi,
    private val dao: UserDao,
    private val networkConnectivityObserver: NetworkConnectivityObserver,
) {
    suspend fun loadUsers(): List<User>? {
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val response = api.getUsers()
                dao.upsertAll(response)
                return response
            } catch (e: Exception) {
                return null
            }
        } else {
            return dao.getAllUsers()
        }
    }

    suspend fun loadUserCommentsById(id: Int, currentPage: Int): List<Comment>? {
        if (networkConnectivityObserver.isNetworkAvailable()) {
            try {
                val response = api.getComments(id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE)
                val currentUser = dao.getUserById(id)
                dao.update(currentUser.copy(comments = response.toMutableList()))
                return response
            } catch (e: Exception) {
                return null
            }
        } else {
            return emptyList()
        }
    }
}