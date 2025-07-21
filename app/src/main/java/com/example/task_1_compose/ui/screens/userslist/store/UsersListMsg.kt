package com.example.task_1_compose.ui.screens.userslist.store

import com.example.domain.data.User
import com.example.domain.statefuldata.StatefulData

sealed interface UsersListMsg {
    data class UsersLoaded(val users: List<User>) : UsersListMsg
    data class UsersLoadError(val statefulData: StatefulData<List<User>>) : UsersListMsg
}