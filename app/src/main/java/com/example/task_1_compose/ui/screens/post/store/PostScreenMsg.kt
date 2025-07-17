package com.example.task_1_compose.ui.screens.post.store

import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.statefuldata.StatefulData

sealed interface PostScreenMsg {
    data class LikeClicked(val post: Post) : PostScreenMsg
    data class NextCommentsLoaded(val post: Post) : PostScreenMsg
    data object AllCommentsLoaded : PostScreenMsg
    data class CommentsLoadError(val statefulData: StatefulData<List<Comment>>) : PostScreenMsg
}