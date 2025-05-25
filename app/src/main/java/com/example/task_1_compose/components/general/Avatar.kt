package com.example.task_1_compose.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R

@Composable
fun Avatar(
    username: String,
    size: Dp,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = dimensionResource(R.dimen.text_small).value.sp,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(color = colorResource(R.color.light_gray), shape = CircleShape)
            .border(
                width = dimensionResource(R.dimen.border_stroke_2),
                color = Color.Black,
                shape = CircleShape
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = username[0].toString() + (if (username.trimEnd()
                    .indexOf(' ') != -1
            ) username[username.indexOf(' ') + 1] else ""),
            fontSize = fontSize
        )
    }
}