package com.example.task_1_compose.components.cards

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.task_1_compose.R
import com.example.task_1_compose.components.general.UserImageAndName
import com.example.task_1_compose.data.dataclasses.Album

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PhotoCard(
    modifier: Modifier,
    index: Int,
    album: Album,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClick: () -> Unit
) {
    val photo = album.photos[index]

    OutlinedCustomCard(
        onClick = onClick,
        modifier = modifier
    ) {
        UserImageAndName(username = photo.name)
        with(sharedTransitionScope) {
            Image(
                painter = painterResource(photo.photo),
                contentDescription = "photo",
                modifier = Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image-${index}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_large)
                    )
            )
        }
    }
}