package com.example.task_1_compose.ui.screens.postslist.store

import com.example.domain.data.Post
import com.example.domain.statefuldata.StatefulData

data class PostsListState(
    val statefulData: StatefulData<List<Post>>,
    val currentPage: Int = 1,
    val currentPosts: List<Post> = emptyList()
)