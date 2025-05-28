package com.example.task_1_compose.components.general

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ImageItem(
    painter: Painter,
    index: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        val zoomState = rememberZoomState(maxScale = 5f)
        with(sharedTransitionScope){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image-${index}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .fillMaxSize()
                    .zoomable(zoomState)
            )
        }

    }
}