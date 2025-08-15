package com.example.domain.utilities.db

import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.data.realm.PostRealm
import com.example.domain.utilities.toCommentRealm
import com.example.domain.utilities.toPost
import com.example.domain.utilities.toPostRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy

internal suspend fun Realm.savePosts(posts: List<Post>) = this.write {
    posts.forEach {
        copyToRealm(
            instance = it.toPostRealm(),
            updatePolicy = UpdatePolicy.ALL
        )
    }
}

internal fun Realm.loadPosts() = this.query(PostRealm::class).find().map { it ->
    it.toPost()
}

internal suspend fun Realm.savePostComments(id: Int, comments: List<Comment>) = write {
    val post = query(PostRealm::class, "id == $0", id)
        .first()
        .find() ?: return@write
    val newCommentRealms = comments.map { comment ->
        comment.toCommentRealm()
    }
    val toAdd = newCommentRealms.filter { newComment ->
        post.comments.none { it.name == newComment.name && it.body == newComment.body }
    }
    post.comments.addAll(toAdd)
}