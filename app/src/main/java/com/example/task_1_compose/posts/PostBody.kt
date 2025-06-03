package com.example.task_1_compose.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.components.general.UserImageAndName
import com.example.task_1_compose.data.dataclasses.Post

@Composable
fun PostBody(
    post: Post,
    onLikeClicked: (Int) -> Unit
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
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = post.description,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_small)
                ),
                fontSize = dimensionResource(R.dimen.text_standard).value.sp
            )
            Icon(
                if (post.likedState) {
                    Icons.Rounded.Favorite
                } else {
                    Icons.Rounded.FavoriteBorder
                },
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_mini)
                    )
                    .size(dimensionResource(R.dimen.heart_size))
                    .clickable { onLikeClicked(post.id) }
            )
        }

    }
}