package com.example.domain.data.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class AlbumRealm : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var photos: RealmList<PhotoRealm> = realmListOf()
}