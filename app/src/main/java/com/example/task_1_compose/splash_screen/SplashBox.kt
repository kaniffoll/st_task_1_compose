package com.example.task_1_compose.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.task_1_compose.R

@Composable
fun SplashBox(modifier: Modifier) {
    Box(
        modifier = modifier
            .width(dimensionResource(R.dimen.splash_box_width))
            .height(dimensionResource(R.dimen.splash_box_height))
            .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.splash_box_shape)))
            .border(
                width = dimensionResource(R.dimen.border_stroke_splash_box),
                color = Color.Black,
                shape = RoundedCornerShape(dimensionResource(R.dimen.splash_box_shape))
            )
            .background(Color.White)
    )
}