package com.example.domain.data.realm

import io.realm.kotlin.types.EmbeddedRealmObject

class CommentRealm: EmbeddedRealmObject {
    var name: String = ""
    var body: String = ""
}