package com.example.domain.data.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TodoRealm : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var completed: Boolean = false
}