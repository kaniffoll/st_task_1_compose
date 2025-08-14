package com.example.domain.repositories

import com.example.domain.api.post.PostApi
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.data.realm.PostRealm
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import com.example.domain.utilities.NetworkConnectivityObserver
import com.example.domain.utilities.toCommentRealm
import com.example.domain.utilities.toPost
import com.example.domain.utilities.toPostRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy

class PostsRepository(
    private val api: PostApi,
    private val realm: Realm,
    private val connectivityObserver: NetworkConnectivityObserver,
) {
    private var posts = mutableListOf<Post>()
    private var allCommentsLoadedFromDB = false

    suspend fun loadNextPosts(currentPage: Int): List<Post>? {
        if (connectivityObserver.isNetworkAvailable()) {
            val response = api.getPosts(
                start = currentPage * POSTS_PER_PAGE, limit = POSTS_PER_PAGE
            ) ?: return null
            realm.write {
                response.forEach {
                    copyToRealm(
                        instance = it.toPostRealm(),
                        updatePolicy = UpdatePolicy.ALL
                    )
                }
            }
            posts.addAll(response)
        } else {
            posts.clear()
            posts.addAll(
                realm.query(PostRealm::class).find().map { it ->
                    it.toPost()
                }
            )
        }
        return posts
    }

    suspend fun loadPostCommentsById(id: Int, currentPage: Int): List<Comment>? {
        if (!connectivityObserver.isNetworkAvailable()) {
            if (allCommentsLoadedFromDB) return emptyList()
            val post = realm.query(PostRealm::class, "id == $0", id)
                .first()
                .find()?.toPost() ?: return null
            allCommentsLoadedFromDB = true
            return post.comments
        }
        val response = api.getComments(
            postId = id, start = currentPage * COMMENTS_PER_PAGE, limit = COMMENTS_PER_PAGE
        ) ?: return null

        realm.write {
            val post = this.query(PostRealm::class, "id == $0", id)
                .first()
                .find() ?: return@write
            val newComments = response.filter { newComment ->
                post.comments.none { it.body == newComment.body && it.name == newComment.name }
            }
            post.comments.addAll(newComments.map { it.toCommentRealm() })
        }
        return response
    }
}
