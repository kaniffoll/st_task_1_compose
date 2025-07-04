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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.domain.data.dataclasses.Comment
import com.example.domain.resources.AppSettings.INITIAL_COMMENTS_COUNT
import com.example.domain.resources.TestTags.COMMENTS_SECTION
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.cards.CommentCard
import com.example.task_1_compose.ui.components.general.LoadingIndicator
import com.example.task_1_compose.ui.components.views.buttons.ErrorButton
import com.example.task_1_compose.ui.components.views.buttons.TextButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CommentsSection(
    modifier: Modifier = Modifier,
    comments: StatefulData<List<Comment>>,
    canLoadMore: () -> Boolean,
    scope: CoroutineScope,
    onRetry: suspend () -> Unit
) {
    var isCommentsExpanded by remember {
        mutableStateOf(
            comments is SuccessData && comments.result.size < INITIAL_COMMENTS_COUNT
        )
    }

    LazyColumn(
        modifier = modifier
            .background(color = Color.White)
            .testTag(COMMENTS_SECTION),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        item { CommentsSectionTitle() }

        when (comments) {
            is LoadingData -> {
                item {
                    LoadingIndicator()
                }
            }

            is ErrorData -> {
                item {
                    ErrorButton {
                        scope.launch {
                            onRetry()
                        }
                    }
                }
            }

            is SuccessData -> {
                isCommentsExpanded = isCommentsExpanded || comments.result.size < INITIAL_COMMENTS_COUNT
                val allComments = comments.result
                val commentsCount =
                    if (isCommentsExpanded) allComments.size else INITIAL_COMMENTS_COUNT

                items(commentsCount) { index ->
                    CommentCard(comment = allComments[index])
                }

                if (canLoadMore() && isCommentsExpanded) {
                    item {
                        TextButton(
                            text = stringResource(R.string.load_more),
                            color = Color.Gray
                        ) {
                            scope.launch {
                                onRetry()
                            }
                        }
                    }
                }

                if (allComments.size > INITIAL_COMMENTS_COUNT) {
                    item {
                        CommentsSectionShowMoreButton(isCommentsExpanded) {
                            isCommentsExpanded = !isCommentsExpanded
                        }
                    }
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
