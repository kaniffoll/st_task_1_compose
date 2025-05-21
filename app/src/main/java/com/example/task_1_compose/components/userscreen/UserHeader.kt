package com.example.task_1_compose.components.userscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.task_1_compose.data.User
import kotlin.random.Random

@Composable
fun UserHeader(user: User){
    val randomColor = Color(
        red = Random.nextInt(0, 255),
        green = Random.nextInt(0, 255),
        blue = Random.nextInt(0, 255)
    )
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
                .height(230.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .border(dimensionResource(R.dimen.border_stroke_2), Color.Black)
                    .background(color = randomColor)
            )
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .graphicsLayer {
                        clip = true
                        shape = CircleShape
                        translationY = 75.dp.toPx()
                    }
                    .background(Color(0xFFd5d5d5))
                    .border(
                        dimensionResource(R.dimen.border_stroke_2),
                        Color.Black,
                        shape = RoundedCornerShape(100)
                    )
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = user.name[0].toString() + (if (user.name.trimEnd()
                            .indexOf(' ') != -1
                    ) user.name[user.name.indexOf(' ') + 1] else ""),
                    fontSize = 60.sp,
                )
            }
        }

        Spacer(Modifier.height(75.dp))
        Text(
            text = user.name,
            fontSize = 40.sp,
        )
    }
}