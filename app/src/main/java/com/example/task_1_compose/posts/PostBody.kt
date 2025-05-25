package com.example.task_1_compose.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.components.general.UserImageAndName
import com.example.task_1_compose.data.dataclasses.Post

@Composable
fun PostBody(post: Post) {
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
        Text(
            text = post.description,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_small)
            ),
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )
    }
}