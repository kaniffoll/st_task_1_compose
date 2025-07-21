package com.example.task_1_compose.ui.screens.userslist.store

import com.example.domain.data.User
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData

data class UsersListState(
    val statefulData: StatefulData<List<User>> = LoadingData(),
    val currentUsers: List<User> = emptyList()
)
