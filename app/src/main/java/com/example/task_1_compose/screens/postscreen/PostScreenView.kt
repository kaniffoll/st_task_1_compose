package com.example.task_1_compose.screens.postscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task_1_compose.R
import com.example.task_1_compose.components.general.CommentsSection
import com.example.task_1_compose.components.general.UserImageAndName
import com.example.task_1_compose.data.dataclasses.Post

@Composable
fun PostScreen(
    post: Post,
) {
    val postScreenViewModel: PostScreenViewModel = viewModel { PostScreenViewModel(post) }
    val currentPost by postScreenViewModel.postState.collectAsState()
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
            postScreenViewModel.toggleLike()
        }
        CommentsSection(post = currentPost)
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
        Text(
            text = post.title,
            modifier = Modifier
                .padding(
                    dimensionResource(R.dimen.padding_small)
                ),
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )
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
            Icon(
                if (post.isLiked) {
                    Icons.Rounded.Favorite
                } else {
                    Icons.Rounded.FavoriteBorder
                },
                contentDescription = stringResource(R.string.like_icon),
                modifier = Modifier
                    .padding(
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_mini)
                    )
                    .size(dimensionResource(R.dimen.heart_size))
                    .clickable { onLikeClicked() }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(
        Post(
            0,
            "User 1",
            "Title",
            description = "Description",
        )
    )
}