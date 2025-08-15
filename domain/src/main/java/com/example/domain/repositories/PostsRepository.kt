package com.example.domain.repositories

import com.example.domain.api.post.PostApi
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.utilities.db.loadPosts
import com.example.domain.utilities.db.savePostComments
import com.example.domain.utilities.db.savePosts
import io.realm.kotlin.Realm

class PostsRepository(
    private val api: PostApi,
    private val realm: Realm,
    private val connectivityObserver: NetworkConnectivityObserver,
) {
    private var posts = mutableListOf<Post>()

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        if (connectivityObserver.isNetworkAvailable()) {
            val response = api.getPosts(
                start = currentPage * POSTS_PER_PAGE, limit = POSTS_PER_PAGE
            ) ?: return null
            realm.savePosts(response)
            posts.addAll(response)
        } else {
            posts.clear()
            posts.addAll(
                realm.loadPosts()
            )
        }
        return posts
    }

    suspend fun loadPostCommentsById(id: Int, currentPage: Int): List<Comment>? {
        if (!connectivityObserver.isNetworkAvailable()) {
            return emptyList()
        }
        val response = api.getComments(
            postId = id, start = currentPage * COMMENTS_PER_PAGE, limit = COMMENTS_PER_PAGE
        ) ?: return null

        realm.savePostComments(id, response)
        return response
    }
}
