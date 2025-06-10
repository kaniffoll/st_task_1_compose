package com.example.task_1_compose.ui.components.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.domain.data.dataclasses.Comment
import com.example.task_1_compose.R
import com.example.task_1_compose.resources.AppSettings.INITIAL_COMMENTS_COUNT
import com.example.task_1_compose.ui.components.cards.CommentCard
import com.example.task_1_compose.ui.components.views.buttons.TextButton

@Composable
fun CommentsSection(
    modifier: Modifier = Modifier,
    comments: List<Comment>
) {

    var isCommentsExpanded by remember { mutableStateOf(comments.size < INITIAL_COMMENTS_COUNT) }
    val commentsCount = if (isCommentsExpanded) comments.size else INITIAL_COMMENTS_COUNT

    LazyColumn(
        modifier = modifier.background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item { CommentsSectionTitle() }

        items(commentsCount) { index ->
            CommentCard(comment = comments[index])
        }

        if (comments.size > INITIAL_COMMENTS_COUNT) {
            item {
                CommentsSectionShowMoreButton(isCommentsExpanded = isCommentsExpanded) {
                    isCommentsExpanded = !isCommentsExpanded
                }
            }
        }
    }
}

@Composable
fun CommentsSectionTitle() {
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

@Composable
fun CommentsSectionShowMoreButton(
    isCommentsExpanded: Boolean,
    onClick: () -> Unit
) {
    val text = if (!isCommentsExpanded) {
        stringResource(R.string.show_more)
    } else {
        stringResource(R.string.show_less)
    }

    TextButton(text = text, onClick = onClick)
}