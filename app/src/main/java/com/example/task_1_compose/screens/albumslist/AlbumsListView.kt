package com.example.task_1_compose.screens.albumslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.OutlinedCustomCard
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.navigation.AlbumScreenRoute

@Composable
fun AlbumsList(
    navController: NavController
) {
    val albumsListViewModel: AlbumsListViewModel = viewModel()
    val albums by albumsListViewModel.albumsState.collectAsState()
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(
                dimensionResource(R.dimen.padding_small)
            )
    ) {
        items(albums) {
            AlbumCard(
                album = it
            ) {
                navController.navigate(AlbumScreenRoute(it.id))
            }
        }
    }
}

@Composable
fun AlbumCard(
    album: Album,
    onClick: () -> Unit
) {
    OutlinedCustomCard(onClick = onClick, modifier = Modifier) {
        Text(
            text = album.name,
            fontSize = dimensionResource(R.dimen.text_small).value.sp,
            modifier = Modifier.padding(
                dimensionResource(R.dimen.padding_medium)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumCardPreview() {
    AlbumCard(Album(-1, "Album -1", photos = emptyList())) { }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlbums() {
    AlbumsList(
        navController = rememberNavController()
    )
}