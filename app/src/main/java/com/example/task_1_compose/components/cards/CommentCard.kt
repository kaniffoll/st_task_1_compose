package com.example.task_1_compose.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R

@Composable
fun CommentCard(
    username: String,
    commentText: String,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium_2))
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = dimensionResource(R.dimen.border_stroke_2),
                    color = Color.Black
                )
            ),
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(
            text = username,
            fontSize = dimensionResource(R.dimen.text_comment).value.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_medium)
                )
        )
        Text(
            text = commentText,
            fontSize = dimensionResource(R.dimen.text_standard).value.sp,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                    bottom = dimensionResource(R.dimen.padding_medium)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserCommentPreview() {
    CommentCard(
        username = "User1",
        commentText = "blablablablablablablablablablablablablalbablablabla"
    )
}