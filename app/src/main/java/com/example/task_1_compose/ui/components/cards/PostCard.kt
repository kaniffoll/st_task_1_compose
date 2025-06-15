package com.example.task_1_compose.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.domain.data.dataclasses.Post
import com.example.task_1_compose.ui.components.views.buttons.LikeButton
import com.example.task_1_compose.ui.components.general.UserImageAndName

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier,
    onLikeClicked: (Int) -> Unit,
    onClick: () -> Unit
) {
    OutlinedCustomCard(onClick = onClick, modifier = modifier) {
        UserImageAndName(post.userId.toString())
        Text(
            text = post.title,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small)),
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )
        PostCardDescriptionContent(post, onLikeClicked = onLikeClicked)
    }
}

@Composable
fun PostCardDescriptionContent(
    post: Post,
    onLikeClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = post.description,
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.padding_small))
                .weight(1f)
                .padding(end = dimensionResource(R.dimen.padding_small)),
            fontSize = dimensionResource(R.dimen.text_standard).value.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        LikeButton(status = post.isLiked) {
            onLikeClicked(post.id)
        }
    }
}