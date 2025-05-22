package com.example.task_1_compose.users.user_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.data.User

@Composable
fun UserAndUsername(user: User) {
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
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen.avatar_small))
                .background(
                    color = colorResource(R.color.light_gray),
                    shape = CircleShape
                )
                .border(
                    dimensionResource(R.dimen.border_stroke_2),
                    Color.Black,
                    shape = CircleShape
                )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = user.name[0].toString() + (if (user.name.trimEnd()
                        .indexOf(' ') != -1
                ) user.name[user.name.indexOf(' ') + 1] else ""),
                fontSize = dimensionResource(R.dimen.text_small).value.sp
            )
        }
        Column {
            Text(text = user.name)
            Text(text = user.username)
        }
    }
}