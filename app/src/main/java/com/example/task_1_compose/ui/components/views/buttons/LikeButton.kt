package com.example.task_1_compose.ui.components.views.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.task_1_compose.R

@Composable
fun LikeButton(
    status: Boolean,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    Icon(
        if (status) {
            Icons.Rounded.Favorite
        } else {
            Icons.Rounded.FavoriteBorder
        },
        contentDescription = if (status) stringResource(R.string.filled_like_icon) else stringResource(R.string.like_icon),
        modifier = modifier
            .padding(
                end = dimensionResource(R.dimen.padding_medium),
                bottom = dimensionResource(R.dimen.padding_mini)
            )
            .size(dimensionResource(R.dimen.heart_size))
            .clickable { onClicked() }
    )
}