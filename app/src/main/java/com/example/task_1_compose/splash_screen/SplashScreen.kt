package com.example.task_1_compose.splash_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.PostListRoute
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

private const val stepDuration = 400L

private fun createSteps(px: Int): List<Pair<Int, Int>> = listOf(
    0 to 0,
    0 to px,
    0 to 0,
    px to 0,
    0 to 0,
    0 to -px,
    0 to 0,
    -px to 0,
    0 to 0
)

@Composable
fun SplashScreen(navController: NavController) {
    var visible by remember { mutableStateOf(true) }

    val pxToMove = with(LocalDensity.current) {
        dimensionResource(R.dimen.splash_box_offset).toPx().roundToInt()
    }

    var xOffset by remember { mutableIntStateOf(0) }
    var yOffset by remember { mutableIntStateOf(0) }
    val steps = createSteps(pxToMove)
    val offset by animateIntOffsetAsState(
        targetValue = IntOffset(xOffset, yOffset),
        label = "offset"
    )
    LaunchedEffect(Unit) {
        for ((x, y) in steps) {
            xOffset = x
            yOffset = y
            delay(stepDuration)
        }
        visible = false
        delay(stepDuration)
        navController.navigate(PostListRoute)
    }
    AnimatedVisibility(
        visible = visible,
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            SplashBox(
                Modifier.offset {
                    offset
                }
            )
            SplashBox(Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}