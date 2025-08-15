package com.example.domain.utilities.db

import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.data.realm.UserRealm
import com.example.domain.utilities.toCommentRealm
import com.example.domain.utilities.toUser
import com.example.domain.utilities.toUserRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy

internal suspend fun Realm.saveUsers(users: List<User>) = this.write {
    users.forEach {
        copyToRealm(
            instance = it.toUserRealm(),
            updatePolicy = UpdatePolicy.ALL
        )
    }
}

internal fun Realm.loadUsers() = this.query(UserRealm::class).find().map { it ->
    it.toUser()
}

internal suspend fun Realm.saveUserComments(id: Int, comments: List<Comment>) = write {
    val user = query(UserRealm::class, "id == $0", id)
        .first()
        .find() ?: return@write
    val newCommentRealms = comments.map { comment ->
        comment.toCommentRealm()
    }
    val toAdd = newCommentRealms.filter { newComment ->
        user.comments.none { it.name == newComment.name && it.body == newComment.body }
    }
    user.comments.addAll(toAdd)
}