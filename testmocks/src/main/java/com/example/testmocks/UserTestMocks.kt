package com.example.testmocks

import com.example.domain.data.Address
import com.example.domain.data.Comment
import com.example.domain.data.User

val mockUsers =
    listOf(User(id = 0, name = "", username = "", address = Address(), phone = ""))
val mockUserComments = List(20) { index ->
    Comment(
        name = "comment $index", body = "body $index"
    )
}
const val initCommentsPage = 0
const val mockUserId = 0
val mockUser = User(
    id = 0,
    name = "User 0",
    username = "_username",
    address = Address(
        "", "", "",
    ),
    phone = ""
)