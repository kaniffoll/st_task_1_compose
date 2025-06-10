package com.example.task_1_compose.ui.screens.post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.data.dataclasses.Post
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.containers.CommentsSection
import com.example.task_1_compose.ui.components.general.UserImageAndName
import com.example.task_1_compose.ui.components.views.buttons.LikeButton

@Composable
fun PostScreen(
    post: Post,
) {
    val viewModel: PostScreenViewModel = viewModel { PostScreenViewModel(post) }

    val currentPost by viewModel.post.collectAsState()

    Column(
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
        PostBody(currentPost) {
            viewModel.toggleLike()
        }

        CommentsSection(comments = post.comments)
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
        UserImageAndName(post.username)
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
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = post.description,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_small)
            ),
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )

        LikeButton(status = post.isLiked, onClicked = onLikeClicked)
    }
}