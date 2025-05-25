package com.example.task_1_compose.albums

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.components.general.ImageItem
import com.example.task_1_compose.data.albumsList
import com.example.task_1_compose.data.dataclasses.Album

@Composable
fun ImagePager(
    album: Album,
    initialImage: Int,
    navController: NavController
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
                    painter = painterResource(album.photos[index].second.photo)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PagerPreview() {
    ImagePager(
        album = albumsList[0],
        initialImage = 0,
        navController = rememberNavController()
    )
}