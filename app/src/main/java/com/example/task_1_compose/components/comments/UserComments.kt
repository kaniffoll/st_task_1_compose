package com.example.task_1_compose.components.comments

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.users.user_components.UserCommentCard
import com.example.task_1_compose.users.user_components.UserHeader
import com.example.task_1_compose.data.User

@Composable
fun UserComments(
    user: User,
    modifier: Modifier
) {
    var count by remember { mutableIntStateOf(user.comments.size) }
    var canLoadMore by remember { mutableStateOf(2 < user.comments.size) }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            UserHeader(user)
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
           UserCommentCard(user, index)
        }
        item {
            if (2 < user.comments.size) {
                Text(
                    text = if (canLoadMore) {
                        stringResource(R.string.show_more)
                    } else {
                        stringResource(R.string.show_less)
                    },
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(R.dimen.padding_small),
                            bottom = dimensionResource(R.dimen.padding_medium_2)
                        )
                        .fillMaxWidth()
                        .clickable {
                            canLoadMore = !canLoadMore
                            count = if (!canLoadMore) user.comments.size else 2
                        },
                    fontSize = dimensionResource(R.dimen.text_comment).value.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
