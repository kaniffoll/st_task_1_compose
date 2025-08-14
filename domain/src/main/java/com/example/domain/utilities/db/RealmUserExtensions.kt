package com.example.domain.utilities.db

import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.data.realm.UserRealm
import com.example.domain.utilities.toCommentRealm
import com.example.domain.utilities.toUser
import com.example.domain.utilities.toUserRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy

suspend fun Realm.saveUsers(users: List<User>) = this.write {
    users.forEach {
        copyToRealm(
            instance = it.toUserRealm(),
            updatePolicy = UpdatePolicy.ALL
        )
    }
}

fun Realm.loadUsers() = this.query(UserRealm::class).find().map { it ->
    it.toUser()
}

fun Realm.getUserById(id: Int) = this.query(UserRealm::class, "id == $0", id)
    .first()
    .find()?.toUser()

suspend fun Realm.saveUserComments(id: Int, comments: List<Comment>) = this.write {
    val user = this.query(UserRealm::class, "id == $0", id)
        .first()
        .find() ?: return@write
    val newComments = comments.filter { newComment ->
        user.comments.none { it.body == newComment.body && it.name == newComment.name }
    }
    user.comments.addAll(newComments.map { it.toCommentRealm() })
}