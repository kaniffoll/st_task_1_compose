package com.example.domain.data.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PostRealm: RealmObject {
    @PrimaryKey
    var id: Int = 0
    var userId: Int = 0
    var title: String = ""
    var body: String = ""
    var comments: RealmList<CommentRealm> = realmListOf()
    var isLiked: Boolean = false
}