package com.example.domain.data.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class UserRealm : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var username: String = ""
    var address: AddressRealm? = AddressRealm()
    var comments: RealmList<CommentRealm> = realmListOf()
    var phone: String = ""
}