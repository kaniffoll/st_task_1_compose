package com.example.task_1_compose.data

import androidx.compose.runtime.mutableStateListOf
import com.example.task_1_compose.R
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.data.dataclasses.Comment
import com.example.task_1_compose.data.dataclasses.NavBarItem
import com.example.task_1_compose.data.dataclasses.Photo
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.data.dataclasses.User
import com.example.task_1_compose.navigation.AlbumsListRoute
import com.example.task_1_compose.navigation.ImagePagerRoute
import com.example.task_1_compose.navigation.PostListRoute
import com.example.task_1_compose.navigation.SplashScreenRoute
import com.example.task_1_compose.navigation.TodosListRoute
import com.example.task_1_compose.navigation.UsersListRoute

//Фейковые данные постов
val postsList = mutableStateListOf(
    Post(id = 0, username = "User 0", title = "Title", description = "Description"),
    Post(id = 1, username = "User 1", title = "Title", description = "Description"),
    Post(id = 2, username = "User 2", title = "Title", description = "Description"),
    Post(id = 3, username = "User 3", title = "Title", description = "Description"),
    Post(id = 4, username = "User 4", title = "Title", description = "Description"),
    Post(
        id = 5,
        username = "User 5",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 1", "Interesting post, thanks for sharing!"),
            Comment("User 2", "I have a similar experience with this"),
            Comment("User 3", "Could you elaborate on the second point?"),
            Comment("User 4", "This solved my problem, much appreciated"),
            Comment("User 5", "Thanks everyone for the feedback!")
        )
    ),
    Post(id = 6, username = "User 6", title = "Title", description = "Description"),
    Post(
        id = 7,
        username = "User 7",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 2", "Helpful information, bookmarked!"),
            Comment("User 4", "How does this compare to other solutions?")
        )
    ),
    Post(id = 8, username = "User 8", title = "Title", description = "Description"),
    Post(id = 9, username = "User 9", title = "Title", description = "Description"),
    Post(
        id = 10,
        username = "User 10",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 3", "Clear and concise explanation"),
            Comment("User 6", "Working on something similar right now"),
            Comment("User 8", "Any updates on this topic?")
        )
    ),
    Post(id = 11, username = "User 11", title = "Title", description = "Description"),
    Post(id = 12, username = "User 12", title = "Title", description = "Description"),
    Post(
        id = 13,
        username = "User 13",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 1", "First comment here!"),
            Comment("User 5", "This deserves more attention"),
            Comment("User 7", "Agree with the previous comments"),
            Comment("User 12", "Sharing with my team")
        )
    ),
    Post(id = 14, username = "User 14", title = "Title", description = "Description"),
    Post(id = 15, username = "User 15", title = "Title", description = "Description"),
    Post(
        id = 16,
        username = "User 16",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 4", "Exactly what I was looking for"),
            Comment("User 9", "How long did this take to implement?")
        )
    ),
    Post(id = 17, username = "User 17", title = "Title", description = "Description"),
    Post(id = 18, username = "User 18", title = "Title", description = "Description"),
    Post(
        id = 19,
        username = "User 19",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 3", "Simple but effective solution"),
            Comment("User 10", "Can this be applied to other cases?"),
            Comment("User 15", "Works perfectly, thank you!"),
            Comment("User 18", "Why didn't I think of this before?")
        )
    ),
    Post(
        id = 20,
        username = "User 20",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 1", "Great starting point for beginners"),
            Comment("User 5", "Adding my own experience to this"),
            Comment("User 10", "What about edge cases?"),
            Comment("User 15", "This saved me hours of work"),
            Comment("User 20", "Appreciate all the engagement!")
        )
    ),
    Post(
        id = 21,
        username = "User 21",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 3", "This approach worked perfectly for our use case"),
            Comment("User 7", "Have you considered the performance implications?"),
            Comment("User 12", "We implemented something similar last quarter")
        )
    ),
    Post(
        id = 22,
        username = "User 22",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 5", "Clear and concise explanation, thanks!"),
            Comment("User 18", "How does this scale with larger datasets?")
        )
    ),
    Post(
        id = 23,
        username = "User 23",
        title = "Title",
        description = "Description"
    ),
    Post(
        id = 24,
        username = "User 24",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 2", "Game changer for our workflow"),
            Comment("User 9", "Any gotchas we should be aware of?"),
            Comment("User 15", "Confirmed this works in production"),
            Comment("User 20", "Shared with my entire team")
        )
    ),
    Post(
        id = 25,
        username = "User 25",
        title = "Title",
        description = "Description"
    ),
    Post(
        id = 26,
        username = "User 26",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 4", "Solved our immediate problem"),
            Comment("User 10", "Would love to see benchmarks"),
            Comment("User 17", "Alternative approach might be...")
        )
    ),
    Post(
        id = 27,
        username = "User 27",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 1", "First! This is exactly what I needed"),
            Comment("User 8", "Documentation reference would help"),
            Comment("User 14", "Works as advertised"),
            Comment("User 21", "Adapted this for our special case"),
            Comment("User 27", "Thanks for all the feedback!")
        )
    ),
    Post(
        id = 28,
        username = "User 28",
        title = "Title",
        description = "Description"
    ),
    Post(
        id = 29,
        username = "User 29",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 6", "Why didn't I find this earlier?"),
            Comment("User 13", "Any plans to extend this functionality?")
        )
    ),
    Post(
        id = 30,
        username = "User 30",
        title = "Title",
        description = "Description",
        comments = mutableListOf(
            Comment("User 2", "Bookmarking this for future reference"),
            Comment("User 5", "The community needs more posts like this"),
            Comment("User 11", "Minor tweak needed for our environment"),
            Comment("User 19", "This deserves more upvotes"),
            Comment("User 30", "Glad to contribute to the discussion!")
        )
    )
)

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

const val address = "Воронеж, улица Мира 105"
const val phone = "8 900 100 20 30"

val usersList: List<User> = listOf(
    User(
        name = "User 1",
        username = "@username_1",
        comments = mutableListOf(),
        address = address,
        phone = phone
    ),
    User(
        name = "User 2",
        username = "@username_2",
        comments = mutableListOf(
            "Blablahblahalahblahblhablahhbalhblahb",
            "JBLjBLJBLJBlJBHJHBKHBJKHBKHBKkhbkhbkHBK",
            "jfLJFkljdlkfjLKJFdkljflkdJKLFjlkDfD",
            "jDLKJflDJFLKjkldflkdjLKFJdklfjDLKjfdL"
        ),
        address = address,
        phone = phone
    ),
    User(
        name = "User 3",
        username = "@username_3",
        comments = mutableListOf(
            "BLAhblahblahblah",
            "hJKGHksgdhkjghKjgsd"
        ),
        address = address,
        phone = phone
    ),
    User(
        name = "User 5",
        username = "@username_4",
        comments = mutableListOf(),
        address = address,
        phone = phone
    )
)

val navBarItems = listOf(
    NavBarItem(
        title = R.string.posts,
        color = R.color.blue_for_posts,
        appRoute = PostListRoute
    ),
    NavBarItem(
        title = R.string.photos,
        color = R.color.orange_for_photos,
        appRoute = AlbumsListRoute
    ),
    NavBarItem(
        title = R.string.todos,
        color = R.color.red_for_todos,
        appRoute = TodosListRoute
    ),
    NavBarItem(
        title = R.string.users,
        color = R.color.cyan_for_users,
        appRoute = UsersListRoute
    )
)

val EmptyTopBars = listOf(
    PostListRoute::class.simpleName.toString(),
    AlbumsListRoute::class.simpleName.toString(),
    TodosListRoute::class.simpleName.toString(),
    UsersListRoute::class.simpleName.toString(),
)

val EmptyBottomBars = listOf(
    ImagePagerRoute::class.simpleName.toString(),
    SplashScreenRoute::class.simpleName.toString()
)