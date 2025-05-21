package com.example.task_1_compose.albums

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.data.albumsList

@Composable
fun ImagePager(
    albumIndex: Int,
    initialImage: Int,
    navController: NavController
) {
    val album = albumsList[albumIndex]
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
                Box(
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.top_appbar_height))
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium)
                        ),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.back_icon_size))
                            .clickable { navController.popBackStack() },
                    )
                }
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(album.photos[index].second.photo),
                        contentDescription = "$index"
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PagerPreview() {
    ImagePager(
        albumIndex = 0,
        initialImage = 0,
        navController = rememberNavController()
    )
}