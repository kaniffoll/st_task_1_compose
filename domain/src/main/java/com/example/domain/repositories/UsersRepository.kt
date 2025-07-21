package com.example.domain.repositories

import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.mocks.usersList
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import kotlinx.coroutines.delay

class UsersRepository {
    private var allCommentsLoaded = false
    suspend fun loadUsers(): List<User>? {
        delay(1000L)
        return try {
            usersList
        } catch (e: Exception) {
            null
        }
    }

    suspend fun loadUserCommentsById(id: Int, currentPage: Int): List<Comment>? {
        delay(1000L)
        val currentUser = usersList.firstOrNull { it.id == id } ?: return null
        try {
            val startIndex = currentPage * COMMENTS_PER_PAGE
            val endIndex =
                (startIndex + COMMENTS_PER_PAGE).coerceAtMost(currentUser.comments.size)

            if (startIndex >= currentUser.comments.size) {
                allCommentsLoaded = true
                return emptyList()
            }
            return currentUser.comments.subList(startIndex, endIndex)
        } catch (e: Exception) {
            return null
        }
    }
}