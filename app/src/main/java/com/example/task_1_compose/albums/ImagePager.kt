package com.example.task_1_compose.albums

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.task_1_compose.data.albumsList

@Composable
fun ImagePager(
    albumIndex: Int,
    initialImage: Int,
    onClose: () -> Unit
) {
    val album = albumsList[albumIndex]
    val pageState = rememberPagerState(initialPage = initialImage) { album.photos.size }

    BackHandler {
        onClose()
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
                        .weight(1f)
                        .padding(start = 10.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp).clickable { onClose() },
                    )
                }
                Box(
                    modifier = Modifier.weight(9f),
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
    ) { }
}