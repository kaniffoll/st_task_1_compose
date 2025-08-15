package com.example.domain.repositories

import com.example.domain.api.user.UserApi
import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.utilities.db.loadUsers
import com.example.domain.utilities.db.saveUserComments
import com.example.domain.utilities.db.saveUsers
import io.realm.kotlin.Realm

class UsersRepository(
    private val api: UserApi,
    private val realm: Realm,
    private val connectivityObserver: NetworkConnectivityObserver,
) {

    suspend fun loadUsers(): List<User>? {
        if (connectivityObserver.isNetworkAvailable()) {
            val response = api.getUsers() ?: return null
            realm.saveUsers(response)
            return response
        }
        return realm.loadUsers()
    }

    suspend fun loadUserCommentsById(id: Int, currentPage: Int): List<Comment>? {
        if (!connectivityObserver.isNetworkAvailable()) {
            return emptyList()
        }
        val response = api.getComments(
            id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE
        ) ?: return null

        realm.saveUserComments(id, response)
        return response
    }
}