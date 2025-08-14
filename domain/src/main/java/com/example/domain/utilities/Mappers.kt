package com.example.domain.utilities

import com.example.domain.data.Address
import com.example.domain.data.Album
import com.example.domain.data.Comment
import com.example.domain.data.Photo
import com.example.domain.data.Post
import com.example.domain.data.Todo
import com.example.domain.data.User
import com.example.domain.data.realm.AddressRealm
import com.example.domain.data.realm.AlbumRealm
import com.example.domain.data.realm.CommentRealm
import com.example.domain.data.realm.PhotoRealm
import com.example.domain.data.realm.PostRealm
import com.example.domain.data.realm.TodoRealm
import com.example.domain.data.realm.UserRealm
import io.realm.kotlin.ext.toRealmList

fun Album.toAlbumRealm(): AlbumRealm {
    return AlbumRealm().apply {
        id = this@toAlbumRealm.id
        title = this@toAlbumRealm.title
        photos = this@toAlbumRealm.photos.map { it.toPhotoRealm() }.toRealmList()
    }
}

fun PhotoRealm.toPhoto(): Photo {
    return Photo(
        title = this.title,
        photo = this.photo
    )
}

fun AlbumRealm.toAlbum(): Album {
    return Album(
        id = this.id,
        title = this.title,
        photos = this.photos.map { it.toPhoto() }.toMutableList()
    )
}

fun Photo.toPhotoRealm(): PhotoRealm {
    return PhotoRealm().apply {
        title = this@toPhotoRealm.title
        photo = this@toPhotoRealm.photo
    }
}

fun PostRealm.toPost(): Post {
    return Post(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body,
        comments = this.comments.map { it.toComment() }.toMutableList(),
        isLiked = this.isLiked
    )
}

fun Post.toPostRealm(): PostRealm {
    return PostRealm().apply {
        id = this@toPostRealm.id
        userId = this@toPostRealm.userId
        title = this@toPostRealm.title
        body = this@toPostRealm.body
        comments = this@toPostRealm.comments.map { it.toCommentRealm() }.toRealmList()
        isLiked = this@toPostRealm.isLiked
    }
}

fun CommentRealm.toComment(): Comment {
    return Comment(
        name = this.name,
        body = this.body
    )
}

fun Comment.toCommentRealm(): CommentRealm {
    return CommentRealm().apply {
        name = this@toCommentRealm.name
        body = this@toCommentRealm.body
    }
}

fun UserRealm.toUser(): User {
    return User(
        id = this.id,
        name = this.name,
        username = this.username,
        comments = this.comments.map { it.toComment() }.toMutableList(),
        address = this.address?.toAddress() ?: Address("", "", ""),
        phone = this@toUser.phone
    )
}

fun User.toUserRealm(): UserRealm {
    return UserRealm().apply {
        id = this@toUserRealm.id
        name = this@toUserRealm.name
        username = this@toUserRealm.username
        comments = this@toUserRealm.comments.map { it.toCommentRealm() }.toRealmList()
        address = this@toUserRealm.address.toAddressRealm()
        phone = this@toUserRealm.phone
    }
}

fun Address.toAddressRealm(): AddressRealm {
    return AddressRealm().apply {
        street = this@toAddressRealm.street
        suite = this@toAddressRealm.suite
        city = this@toAddressRealm.city
    }
}

fun AddressRealm.toAddress(): Address {
    return Address(
        street = this.street,
        suite = this.suite,
        city = this.city
    )
}

fun Todo.toTodoRealm(): TodoRealm {
    return TodoRealm().apply {
        id = this@toTodoRealm.id
        title = this@toTodoRealm.title
        completed = this@toTodoRealm.completed
    }
}

fun TodoRealm.toTodo(): Todo {
    return Todo(
        id = this.id,
        title = this.title,
        completed = this.completed
    )
}