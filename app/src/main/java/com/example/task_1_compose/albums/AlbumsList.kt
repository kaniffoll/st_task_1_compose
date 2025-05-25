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
import com.example.task_1_compose.navigation.AlbumScreenRoute
import com.example.task_1_compose.data.albumsList

@Composable
fun AlbumsList(navController: NavController) {
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(
                dimensionResource(R.dimen.padding_small)
            )
    ) {
        items(albumsList) {
            AlbumCard(
                album = it
            ) {
                navController.navigate(AlbumScreenRoute(album = it))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlbums() {
    AlbumsList(
        navController = rememberNavController()
    )
}