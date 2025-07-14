package com.example.testmocks

import com.example.domain.data.Comment
import com.example.domain.data.Post

val mockPosts = List(20) { index ->
    Post(
        id = index + 1,
        userId = (index % 5) + 1,
        title = "Post Title ${index + 1}",
        body = "Post Text ${index + 1}"
    )
}
val mockPostComments = listOf(Comment(name = "", body = ""))
const val initPostsPage = 0
const val mockPostId = 0
val mockPost = Post(
    id = 0,
    userId = 0,
    title = "",
    body = ""
)