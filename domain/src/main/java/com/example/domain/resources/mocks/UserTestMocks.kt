package com.example.domain.resources.mocks

import com.example.domain.data.dataclasses.Address
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.User

val mockUsers =
    listOf(User(id = 0, name = "", username = "", address = Address(), phone = ""))
val mockUserComments = List(20) { index ->
    Comment(
        name = "comment $index", body = "body $index"
    )
}