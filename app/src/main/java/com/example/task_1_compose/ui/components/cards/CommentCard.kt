package com.example.task_1_compose.ui.components.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.domain.data.dataclasses.Comment
import com.example.domain.resources.TestTags.COMMENT_CARD
import com.example.task_1_compose.R

@Composable
fun CommentCard(
    comment: Comment,
) {
    val username = comment.name
    val commentText = comment.body
    OutlinedCustomCard(
        itemsSpacedBy = dimensionResource(R.dimen.padding_small), modifier = Modifier.testTag(
            COMMENT_CARD
        )
    ) {
        Text(
            text = username,
            fontSize = dimensionResource(R.dimen.text_comment).value.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                )
        )
        Text(
            text = commentText,
            fontSize = dimensionResource(R.dimen.text_standard).value.sp,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                )
        )
    }
}