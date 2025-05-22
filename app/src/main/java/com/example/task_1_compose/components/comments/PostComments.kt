package com.example.task_1_compose.components.comments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.data.Post

@Composable
fun PostComments(post: Post) {
    var count by remember { mutableIntStateOf(post.comments.size) }
    var canLoadMore by remember { mutableStateOf(2 < post.comments.size) }

    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            Text(
                text = stringResource(R.string.comments),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_small)
                    ),
                fontSize = dimensionResource(R.dimen.text_comment).value.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (canLoadMore) {
            count = 2
        }
        items(count) { index ->
            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            width = dimensionResource(R.dimen.border_stroke_2),
                            color = Color.Black
                        )
                    ),
                verticalArrangement = Arrangement
                    .spacedBy(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    text = post.comments[index].author,
                    fontSize = dimensionResource(R.dimen.text_comment).value.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_medium)
                        )
                )
                Text(
                    text = post.comments[index].message,
                    fontSize = dimensionResource(R.dimen.text_standard).value.sp,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_small),
                            bottom = dimensionResource(R.dimen.padding_medium)
                        )
                )
            }
        }
        item {
            if (2 < post.comments.size) {
                Text(
                    text = if (canLoadMore) {
                        stringResource(R.string.show_more)
                    } else {
                        stringResource(R.string.show_less)
                    },
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(R.dimen.padding_small),
                            bottom = dimensionResource(R.dimen.padding_medium_2)
                        )
                        .fillMaxWidth()
                        .clickable {
                            canLoadMore = !canLoadMore
                            count = if (!canLoadMore) post.comments.size else 2
                        },
                    fontSize = dimensionResource(R.dimen.text_comment).value.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}