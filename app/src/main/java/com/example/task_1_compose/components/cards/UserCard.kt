package com.example.task_1_compose.components.cards

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.task_1_compose.R
import com.example.task_1_compose.components.general.Avatar
import com.example.task_1_compose.data.dataclasses.User

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserCard(
    user: User,
    modifier: Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClick: () -> Unit
) {
    OutlinedCustomCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(R.dimen.padding_mini_2)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement
                .spacedBy(dimensionResource(R.dimen.padding_small)),
        ) {
            with(sharedTransitionScope) {
                Avatar(
                    user.name,
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "image${user.username}"),
                            animatedVisibilityScope = animatedContentScope
                        ),
                    size = dimensionResource(R.dimen.avatar_small)
                )
            }
            Column {
                with(sharedTransitionScope) {
                    Text(
                        text = user.name,
                        modifier = Modifier
                            .sharedElement(
                                rememberSharedContentState(key = "text${user.username}"),
                                animatedVisibilityScope = animatedContentScope
                            )
                            .fillMaxWidth()
                    )
                }
                Text(text = user.username)
            }
        }
        Text(
            text = stringResource(R.string.address) + " " + user.address,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small)
            ),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.number) + " " + user.phone,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small)
            )
        )
        Spacer(modifier = Modifier)
    }
}