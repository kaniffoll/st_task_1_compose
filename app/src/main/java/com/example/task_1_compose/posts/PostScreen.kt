package com.example.task_1_compose.posts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.task_1_compose.components.cards.CommentCard
import com.example.task_1_compose.data.dataclasses.Post

@Composable
fun PostScreen(
    post: Post,
) {
    var count by remember { mutableIntStateOf(post.comments.size) }
    var canLoadMore by remember { mutableStateOf(2 < post.comments.size) }
    LazyColumn(
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium),
                bottom = dimensionResource(R.dimen.padding_large_2)
            )
            .fillMaxSize()
            .border(
                BorderStroke(
                    dimensionResource(R.dimen.border_stroke_3),
                    Color.Black
                )
            )
            .background(color = Color.White),
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            PostBody(post)
        }
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
            count = 2
        }
        items(count) { index ->
            CommentCard(
                username = post.comments[index].author,
                commentText = post.comments[index].message
            )
        }
        item {
            if (2 < post.comments.size) {
                TextButton(canLoadMore) {
                    canLoadMore = !canLoadMore
                    count = if (!canLoadMore) post.comments.size else 2
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(post =  Post(id = 333, username = "User 1", title = "Title", description = "Description"))
}