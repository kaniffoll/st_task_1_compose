package com.example.task_1_compose.albums

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.AlbumCard
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.navigation.AlbumScreenRoute

@Composable
fun AlbumsList(
    navController: NavController,
    state: List<Pair<String, Int>>,
    onAlbumCLick: (String, Int) -> Album
) {
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(
                dimensionResource(R.dimen.padding_small)
            )
    ) {
        items(state) {
            AlbumCard(
                name = it.first
            ) {
                val album = onAlbumCLick(it.first, it.second)
                navController.navigate(AlbumScreenRoute(album = album))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlbums() {
    AlbumsList(
        navController = rememberNavController(),
        state = listOf("Album 1" to 1)
    ){ _, _ ->
        Album(3, "Album 1", photos = emptyList())
    }
}