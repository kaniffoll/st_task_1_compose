package com.example.task_1_compose.components.comments

import androidx.compose.runtime.Composable
import com.example.task_1_compose.data.DisplayItem

@Composable
fun CommentsSection(
    displayItem: DisplayItem
) {
    when (displayItem) {
        is DisplayItem.PostItem -> {
            PostComments(displayItem.post)
        }

        is DisplayItem.UserItem -> {
            UserComments(displayItem.user)
        }
    }
}