package com.example.task_1_compose.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.components.buttons.TextButton
import com.example.task_1_compose.components.cards.OutlinedCustomCard
import com.example.task_1_compose.data.dataclasses.Comment
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.data.dataclasses.User

private const val INITIAL_COMMENTS_COUNT = 2

@Composable
fun CommentsSection(
    modifier: Modifier = Modifier,
    post: Post? = null,
    user: User? = null,
) {
    val comments = if (user == null && post != null) {
        post.comments
    } else if (user != null && post == null) {
        user.comments
    } else {
        throw (RuntimeException("User and Post == Null"))
    }
    var count by remember { mutableIntStateOf(comments.size) }
    var canLoadMore by remember { mutableStateOf(comments.size > INITIAL_COMMENTS_COUNT) }
    LazyColumn(
        modifier = modifier
            .background(color = Color.White),
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            Text(
                text = stringResource(R.string.comments),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium_2)
                    ),
                fontSize = dimensionResource(R.dimen.text_comment).value.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (canLoadMore) {
            count = INITIAL_COMMENTS_COUNT
        }
        items(count) { index ->
            when (val comment = comments[index]) {
                is String -> {
                    if (user != null) {
                        CommentCard(
                            comment = Comment(user.username, comment)
                        )
                    }
                }

                is Comment -> {
                    CommentCard(
                        comment = comment
                    )
                }
            }
        }
        item {
            if (comments.size > INITIAL_COMMENTS_COUNT) {
                val text = if (canLoadMore) {
                    stringResource(R.string.show_more)
                } else {
                    stringResource(R.string.show_less)
                }
                TextButton(text) {
                    canLoadMore = !canLoadMore
                    count = if (!canLoadMore) comments.size else INITIAL_COMMENTS_COUNT
                }
            }
        }
    }
}

@Composable
fun CommentCard(
    comment: Comment,
) {
    val username = comment.author
    val commentText = comment.message
    OutlinedCustomCard(itemsSpacedBy = dimensionResource(R.dimen.padding_small)) {
        Text(
            text = username,
            fontSize = dimensionResource(R.dimen.text_comment).value.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                )
        )
        Text(
            text = commentText,
            fontSize = dimensionResource(R.dimen.text_standard).value.sp,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserCommentPreview() {
    CommentCard(
        comment = Comment(
            author = "User1",
            message = "blablablablablablablablablablablablablalbablablabla"
        )
    )
}