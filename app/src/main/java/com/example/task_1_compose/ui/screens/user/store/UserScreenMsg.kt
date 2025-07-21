package com.example.task_1_compose.ui.screens.user.store

import com.example.domain.data.Comment
import com.example.domain.data.User
import com.example.domain.statefuldata.StatefulData

sealed interface UserScreenMsg {
    data class NextCommentsLoaded(val user: User) : UserScreenMsg
    data object AllCommentsLoaded : UserScreenMsg
    data class CommentsLoadError(val statefulData: StatefulData<List<Comment>>) : UserScreenMsg
}