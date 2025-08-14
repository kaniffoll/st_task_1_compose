package com.example.domain.data.realm

import androidx.annotation.DrawableRes
import io.realm.kotlin.types.EmbeddedRealmObject

class PhotoRealm : EmbeddedRealmObject {
    var title: String = ""
    @DrawableRes var photo: Int = 0
}