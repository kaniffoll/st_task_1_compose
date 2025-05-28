package com.example.task_1_compose.users

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.components.buttons.TextButton
import com.example.task_1_compose.components.cards.CommentCard
import com.example.task_1_compose.components.headers_and_appbars.UserHeader
import com.example.task_1_compose.data.dataclasses.User

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserScreen(
    user: User,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    var count by remember { mutableIntStateOf(user.comments.size) }
    var canLoadMore by remember { mutableStateOf(2 < user.comments.size) }
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            UserHeader(
                user,
                sharedTransitionScope,
                animatedContentScope
            )
        }
        item {
            Text(
                text = stringResource(R.string.comments),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium)
                    )
                    .fillMaxWidth(),
                fontSize = dimensionResource(R.dimen.text_comment).value.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (canLoadMore) {
            count = 2
        }
        items(count) { index ->
            CommentCard(user.username, user.comments[index])
        }
        item {
            if (2 < user.comments.size) {
                TextButton(canLoadMore) {
                    canLoadMore = !canLoadMore
                    count = if (!canLoadMore) user.comments.size else 2
                }
            }
        }
    }
}