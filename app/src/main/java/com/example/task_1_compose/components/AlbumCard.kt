package com.example.task_1_compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.data.albumsList

@Composable
fun AlbumCard(
    index: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(R.dimen.padding_medium)
            )
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = dimensionResource(R.dimen.border_stroke_2),
                    color = Color.Black
                )
            )
            .clickable{ onClick() }
    ) {
        Text(
            text = albumsList[index].name,
            fontSize = dimensionResource(R.dimen.text_small).value.sp,
            modifier = Modifier.padding(
                dimensionResource(R.dimen.padding_medium)
            )
        )
    }
}