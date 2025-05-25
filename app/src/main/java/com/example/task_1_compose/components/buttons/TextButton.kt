package com.example.task_1_compose.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R

@Composable
fun TextButton(
    canLoadMore: Boolean,
    onClick: () -> Unit
) {
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
                onClick()
            },
        fontSize = dimensionResource(R.dimen.text_comment).value.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}