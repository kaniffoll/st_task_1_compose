package com.example.domain.resources.mocks

import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Post

val mockPosts = List(20) { index ->
    Post(
        id = index + 1,
        userId = (index % 5) + 1,
        title = "Post Title ${index + 1}",
        body = "Post Text ${index + 1}"
    )
}
val mockPostComments = listOf(Comment(name = "", body = ""))