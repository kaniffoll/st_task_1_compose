package com.example.domain.mocks

import androidx.compose.runtime.mutableStateListOf
import com.example.domain.R
import com.example.domain.data.Album
import com.example.domain.data.Comment
import com.example.domain.data.Photo
import com.example.domain.data.Post

val postsList = mutableStateListOf(
    Post(id = 0, userId = 0, title = "Title", body = "body"),
    Post(
        id = 1,
        userId = 1,
        title = "Title",
        body = "D".repeat(100),
        comments = mutableListOf(
            Comment("", "")
        )
    ),
    Post(id = 2, userId = 2, title = "Title", body = "body"),
    Post(id = 3, userId = 3, title = "Title", body = "body"),
    Post(id = 4, userId = 4, title = "Title", body = "body"),
    Post(
        id = 5,
        userId = 5,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 1", "Interesting post, thanks for sharing!"),
            Comment("User 2", "I have a similar experience with this"),
            Comment("User 3", "Could you elaborate on the second point?"),
            Comment("User 4", "This solved my problem, much appreciated"),
            Comment("User 5", "Thanks everyone for the feedback!")
        )
    ),
    Post(id = 6, userId = 6, title = "Title", body = "body"),
    Post(
        id = 7,
        userId = 7,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 2", "Helpful information, bookmarked!"),
            Comment("User 4", "How does this compare to other solutions?")
        )
    ),
    Post(id = 8, userId = 8, title = "Title", body = "body"),
    Post(id = 9, userId = 9, title = "Title", body = "body"),
    Post(
        id = 10,
        userId = 10,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 3", "Clear and concise explanation"),
            Comment("User 6", "Working on something similar right now"),
            Comment("User 8", "Any updates on this topic?")
        )
    ),
    Post(id = 11, userId = 11, title = "Title", body = "body"),
    Post(id = 12, userId = 12, title = "Title", body = "body"),
    Post(
        id = 13,
        userId = 13,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 1", "First comment here!"),
            Comment("User 5", "This deserves more attention"),
            Comment("User 7", "Agree with the previous comments"),
            Comment("User 12", "Sharing with my team")
        )
    ),
    Post(id = 14, userId = 14, title = "Title", body = "body"),
    Post(id = 15, userId = 15, title = "Title", body = "body"),
    Post(
        id = 16,
        userId = 16,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 4", "Exactly what I was looking for"),
            Comment("User 9", "How long did this take to implement?")
        )
    ),
    Post(id = 17, userId = 17, title = "Title", body = "body"),
    Post(id = 18, userId = 18, title = "Title", body = "body"),
    Post(
        id = 19,
        userId = 19,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 3", "Simple but effective solution"),
            Comment("User 10", "Can this be applied to other cases?"),
            Comment("User 15", "Works perfectly, thank you!"),
            Comment("User 18", "Why didn't I think of this before?")
        )
    ),
    Post(
        id = 20,
        userId = 20,
        title = "Title",
        body = "body",
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
        userId = 21,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 3", "This approach worked perfectly for our use case"),
            Comment("User 7", "Have you considered the performance implications?"),
            Comment("User 12", "We implemented something similar last quarter")
        )
    ),
    Post(
        id = 22,
        userId = 22,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 5", "Clear and concise explanation, thanks!"),
            Comment("User 18", "How does this scale with larger datasets?")
        )
    ),
    Post(
        id = 23,
        userId = 23,
        title = "Title",
        body = "body"
    ),
    Post(
        id = 24,
        userId = 24,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 2", "Game changer for our workflow"),
            Comment("User 9", "Any gotchas we should be aware of?"),
            Comment("User 15", "Confirmed this works in production"),
            Comment("User 20", "Shared with my entire team")
        )
    ),
    Post(
        id = 25,
        userId = 25,
        title = "Title",
        body = "body"
    ),
    Post(
        id = 26,
        userId = 26,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 4", "Solved our immediate problem"),
            Comment("User 10", "Would love to see benchmarks"),
            Comment("User 17", "Alternative approach might be...")
        )
    ),
    Post(
        id = 27,
        userId = 27,
        title = "Title",
        body = "body",
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
        userId = 28,
        title = "Title",
        body = "body"
    ),
    Post(
        id = 29,
        userId = 29,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 6", "Why didn't I find this earlier?"),
            Comment("User 13", "Any plans to extend this functionality?")
        )
    ),
    Post(
        id = 30,
        userId = 30,
        title = "Title",
        body = "body",
        comments = mutableListOf(
            Comment("User 2", "Bookmarking this for future reference"),
            Comment("User 5", "The community needs more posts like this"),
            Comment("User 11", "Minor tweak needed for our environment"),
            Comment("User 19", "This deserves more upvotes"),
            Comment("User 30", "Glad to contribute to the discussion!")
        )
    )
)

val albumsList: List<Album> = listOf(
    Album(
        id = 0,
        title = "Album 1", photos = mutableListOf(
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
        title = "Album 2", photos = mutableListOf(
            Photo("User 1", R.drawable.img_4),
            Photo("User 1", R.drawable.img_5),
            Photo("User 4", R.drawable.img_6),
        )
    ),
    Album(
        id = 2,
        title = "Album 3", photos = mutableListOf(
            Photo("User 10", R.drawable.img_7),
            Photo("User 11", R.drawable.img_8),
            Photo("User 7", R.drawable.img_9),
            Photo("User 9", R.drawable.img_10),
        )
    )
)