package com.example.task_1_compose.components.headers_and_appbars

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.components.general.Avatar
import com.example.task_1_compose.data.dataclasses.User
import com.example.task_1_compose.data.usersList
import kotlin.random.Random

@Composable
fun UserHeader(user: User) {
    val randomColor = remember {
        Color(
            red = Random.nextInt(0, 255),
            green = Random.nextInt(0, 255),
            blue = Random.nextInt(0, 255)
        )
    }
    val spacerHeight = dimensionResource(R.dimen.spacer_header_height)
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.color_header_height)),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .border(dimensionResource(R.dimen.border_stroke_2), Color.Black)
                    .background(color = randomColor)
            )
            Avatar(
                username = user.name,
                size = dimensionResource(R.dimen.avatar_large),
                modifier = Modifier
                    .graphicsLayer {
                        clip = true
                        shape = CircleShape
                        translationY = spacerHeight.toPx()
                    },
                fontSize = dimensionResource(R.dimen.text_large_2).value.sp
            )
        }

        Spacer(Modifier.height(spacerHeight))
        Text(
            text = user.name,
            fontSize = dimensionResource(R.dimen.text_large).value.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserHeaderPreview() {
    UserHeader(
        user = usersList[0]
    )
}