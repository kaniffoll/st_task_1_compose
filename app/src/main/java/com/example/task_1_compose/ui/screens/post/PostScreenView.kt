package com.example.task_1_compose.ui.screens.post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.containers.CommentsSection
import com.example.task_1_compose.ui.components.general.UserTile
import com.example.task_1_compose.ui.components.views.buttons.LikeButton

@Composable
fun PostScreen(
    post: Post,
) {
    val viewModel =
        hiltViewModel<PostScreenViewModel, PostScreenViewModel.PostScreenViewModelFactory> { factory ->
            factory.create(post)
        }

    val currentPost by viewModel.post.collectAsState()

    val comments by viewModel.comments.collectAsState()

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
            PostBody(currentPost) {
                viewModel.toggleLike()
            }
        }

        item {
            CommentsSection(
                comments = comments,
                modifier = Modifier.fillParentMaxSize(),
                canLoadMore = { viewModel.canLoadMoreComments() },
                scope = viewModel.viewModelScope
            ) {
                viewModel.loadComments()
            }
        }
    }
}

@Composable
fun PostBody(
    post: Post,
    onLikeClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.padding_small),
                top = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small)
            ),
        verticalArrangement = Arrangement
            .spacedBy(
                dimensionResource(R.dimen.padding_large)
            ),
    ) {
        UserTile(post.userId.toString())
        PostBodyTitle(post)
        PostBodyDescriptionContent(post, onLikeClicked = onLikeClicked)
    }
}

@Composable
fun PostBodyTitle(post: Post) {
    Text(
        text = post.title,
        modifier = Modifier
            .padding(
                dimensionResource(R.dimen.padding_small)
            ),
        fontSize = dimensionResource(R.dimen.text_standard).value.sp
    )
}

@Composable
fun PostBodyDescriptionContent(
    post: Post,
    onLikeClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = post.body,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_small)
            ),
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            LikeButton(status = post.isLiked, onClicked = onLikeClicked)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(
        post = Post(
            id = -1,
            userId = 1,
            title = "Title",
            body = "D".repeat(100),
            comments = mutableListOf(
                Comment("", "")
            )
        )
    )
}