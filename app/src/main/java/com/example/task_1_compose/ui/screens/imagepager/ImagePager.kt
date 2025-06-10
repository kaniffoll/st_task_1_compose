package com.example.task_1_compose.ui.screens.imagepager

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.domain.data.dataclasses.Album
import com.example.task_1_compose.R
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ImagePager(
    album: Album,
    initialImage: Int,
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val pageState = rememberPagerState(initialPage = initialImage) { album.photos.size }

    BackHandler {
        navController.popBackStack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .zIndex(10f)
    ) {
        HorizontalPager(
            state = pageState
        ) { index ->
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                ImageItem(
                    painter = painterResource(album.photos[index].photo),
                    index = initialImage,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope
                )
            }
        }
    }
}

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
        with(sharedTransitionScope) {
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.photo),
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