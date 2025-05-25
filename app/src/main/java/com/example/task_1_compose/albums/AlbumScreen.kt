package com.example.task_1_compose.albums

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.PhotoCard
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.navigation.ImagePagerRoute
import com.example.task_1_compose.data.albumsList

@Composable
fun AlbumScreen(
    album: Album, navController: NavController
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
    ) {
        items(album.photos.size) { id ->
            PhotoCard(
                modifier = Modifier, index = id, album = album, onClick = {
                    currentIndex = id
                    navController.navigate(
                        ImagePagerRoute(
                            album = album, initialImage = currentIndex
                        )
                    )
                })
        }
        item {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.padding_large)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumPreview() {
    AlbumScreen(album = albumsList[0], navController = rememberNavController())
}