package com.example.task_1_compose.ui.screens.post.store

import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

data class PostScreenState(
    val post: Post,
    val statefulData: StatefulData<List<Comment>> = LoadingData(),
    val currentPage: Int = 0
)