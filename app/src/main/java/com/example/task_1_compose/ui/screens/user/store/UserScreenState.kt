package com.example.task_1_compose.ui.screens.user.store

import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

data class UserScreenState(
    val statefulData: StatefulData<List<Comment>> = LoadingData(),
    val currentUser: User,
    val currentPage: Int = 0
)
