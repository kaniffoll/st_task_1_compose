package com.example.domain.data

import com.example.domain.R
import com.example.domain.data.dataclasses.Address
import com.example.domain.data.dataclasses.Album
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Photo
import com.example.domain.data.dataclasses.User

//Фейковые данные фотографий
val albumsList: List<Album> = listOf(
    Album(
        id = 0,
        name = "Album 1", photos = listOf(
            Photo("User 1", R.drawable.img_1),
            Photo("User 2", R.drawable.img_11),
            Photo("User 3", R.drawable.img_2),
            Photo("User 2", R.drawable.img_3),
            Photo("User 2", R.drawable.img_12),
            Photo("User 2", R.drawable.img_13),
            Photo("User 2", R.drawable.img_14),
            Photo("User 2", R.drawable.img_15),
            Photo("User 2", R.drawable.img_16),
            Photo("User 2", R.drawable.img_17),
            Photo("User 2", R.drawable.img_18),
            Photo("User 2", R.drawable.img_19),
            Photo("User 2", R.drawable.img_20),
            Photo("User 2", R.drawable.img_21)
        )
    ),
    Album(
        id = 1,
        name = "Album 2", photos = listOf(
            Photo("User 1", R.drawable.img_4),
            Photo("User 1", R.drawable.img_5),
            Photo("User 4", R.drawable.img_6),
        )
    ),
    Album(
        id = 2,
        name = "Album 3", photos = listOf(
            Photo("User 10", R.drawable.img_7),
            Photo("User 11", R.drawable.img_8),
            Photo("User 7", R.drawable.img_9),
            Photo("User 9", R.drawable.img_10),
        )
    )
)

val address: Address = Address("улица Мира 105", city = "Воронеж")
const val phone = "8 900 100 20 30"

val usersList: List<User> = listOf(
    User(
        id = 1,
        name = "User 1",
        username = "@username_1",
        comments = mutableListOf(),
        address = address,
        phone = phone
    ),
    User(
        id = 2,
        name = "User 2",
        username = "@username_2",
        comments = mutableListOf(
            Comment("User 2", "Bookmarking this for future reference"),
            Comment("User 5", "The community needs more posts like this"),
            Comment("User 11", "Minor tweak needed for our environment"),
            Comment("User 19", "This deserves more upvotes"),
            Comment("User 30", "Glad to contribute to the discussion!")
        ),
        address = address,
        phone = phone
    ),
    User(
        id = 3,
        name = "User 3",
        username = "@username_3",
        comments = mutableListOf(
            Comment("User 2", "Bookmarking this for future reference"),
            Comment("User 5", "The community needs more posts like this"),
            Comment("User 11", "Minor tweak needed for our environment"),
            Comment("User 19", "This deserves more upvotes"),
            Comment("User 30", "Glad to contribute to the discussion!")
        ),
        address = address,
        phone = phone
    ),
    User(
        id = 4,
        name = "User 5",
        username = "@username_4",
        comments = mutableListOf(),
        address = address,
        phone = phone
    )
)
