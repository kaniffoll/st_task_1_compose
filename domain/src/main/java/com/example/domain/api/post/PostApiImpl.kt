package com.example.domain.api.post

import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.resources.AppUrls.BASE_URL
import com.example.domain.resources.AppUrls.COMMENTS
import com.example.domain.resources.AppUrls.POSTS
import com.example.domain.resources.StringResources.LIMIT
import com.example.domain.resources.StringResources.START
import com.example.domain.utilities.e
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments

class PostApiImpl(private val client: HttpClient) : PostApi {
    override suspend fun getPosts(start: Int, limit: Int): List<Post>? {
        return try {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    appendPathSegments(POSTS)
                    parameters.append(START, "$start")
                    parameters.append(LIMIT, "$limit")
                }
            }.body()
        } catch (e: Exception) {
            e.e()
            null
        }
    }

    override suspend fun getComments(postId: Int, start: Int, limit: Int): List<Comment>? {
        return try {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    appendPathSegments(POSTS, "$postId", COMMENTS)
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