package com.example.domain.data.realm

import io.realm.kotlin.types.EmbeddedRealmObject

class AddressRealm : EmbeddedRealmObject {
    var street: String = ""
    var suite: String = ""
    var city: String = ""
}