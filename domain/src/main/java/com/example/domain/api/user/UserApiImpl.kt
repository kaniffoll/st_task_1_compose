package com.example.domain.api.user

import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.resources.AppUrls.BASE_URL
import com.example.domain.resources.AppUrls.COMMENTS
import com.example.domain.resources.AppUrls.USERS
import com.example.domain.resources.StringResources.LIMIT
import com.example.domain.resources.StringResources.START
import com.example.domain.utilities.e
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments

class UserApiImpl(private val client: HttpClient) : UserApi {
    override suspend fun getUsers(): List<User>? {
        return try {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    appendPathSegments(USERS)
                }
            }.body()
        } catch (e: Exception) {
            e.e()
            null
        }
    }

    override suspend fun getComments(userId: Int, start: Int, limit: Int): List<Comment>? {
        return try {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    appendPathSegments(USERS, "$userId", COMMENTS)
                    parameters.append(START, "$start")
                    parameters.append(LIMIT, "$limit")
                }
            }.body()
        } catch (e: Exception) {
            e.e()
            null
        }
    }
}