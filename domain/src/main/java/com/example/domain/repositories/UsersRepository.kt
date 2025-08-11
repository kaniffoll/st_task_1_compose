package com.example.domain.repositories

import com.example.domain.api.user.UserApi
import com.example.domain.data.Comment
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE

class UsersRepository(private val api: UserApi) {
    private var allCommentsLoaded = false
    suspend fun loadUsers() = api.getUsers()

    suspend fun loadUserCommentsById(id: Int, currentPage: Int): List<Comment>? {
        return api.getComments(
            id, currentPage * COMMENTS_PER_PAGE, COMMENTS_PER_PAGE
        )
    }
}