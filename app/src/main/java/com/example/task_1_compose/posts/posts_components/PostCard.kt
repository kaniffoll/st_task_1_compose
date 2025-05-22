package com.example.task_1_compose.posts.posts_components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.components.UserImageAndName
import com.example.task_1_compose.data.Post
import com.example.task_1_compose.data.postsList

@Composable
fun PostCard(
    post: Post, modifier: Modifier, onClick: () -> Unit
) {
    var isLiked by remember { mutableStateOf(false) }
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .fillMaxWidth(),
        shape = RectangleShape,
        border = BorderStroke(dimensionResource(R.dimen.border_stroke_3), Color.Black),
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White,
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
            modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_small),
                    top = dimensionResource(R.dimen.padding_small)
                )
        ) {
            UserImageAndName(post.username)
            Text(
                text = post.title,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small)),
                fontSize = dimensionResource(R.dimen.text_standard).value.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = post.description,
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small)),
                    fontSize = dimensionResource(R.dimen.text_standard).value.sp
                )
                Icon(
                    if (isLiked) {
                        Icons.Rounded.Favorite
                    } else {
                        Icons.Rounded.FavoriteBorder
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(R.dimen.padding_small),
                            bottom = dimensionResource(R.dimen.padding_mini)
                        )
                        .size(dimensionResource(R.dimen.heart_size))
                        .clickable { isLiked = !isLiked },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostCard() {
    PostCard(postsList[0], modifier = Modifier) {}
}

