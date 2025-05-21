package com.example.task_1_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R

@Composable
fun UserImageAndName(username: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement
            .spacedBy(
                dimensionResource(R.dimen.padding_small_2)
            ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen.avatar_small))
                .background(color = Color(0xFFd5d5d5), shape = CircleShape)
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
                fontSize = dimensionResource(R.dimen.text_small).value.sp
            )
        }
        Text(
            text = username,
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )
    }
}