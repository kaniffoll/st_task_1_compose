package com.example.task_1_compose.ui.components.cards

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
import androidx.compose.ui.res.stringResource
import com.example.domain.data.dataclasses.Photo
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.general.UserImageAndName

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PhotoCard(
    modifier: Modifier,
    id: Int,
    photo: Photo,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClick: () -> Unit
) {
    OutlinedCustomCard(
        onClick = onClick,
        modifier = modifier
    ) {
        UserImageAndName(username = photo.name)
        with(sharedTransitionScope) {
            Image(
                painter = painterResource(photo.photo),
                contentDescription = stringResource(R.string.photo),
                modifier = Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image-${id}"),
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