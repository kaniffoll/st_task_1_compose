package com.example.task_1_compose.ui.screens.albumslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.AlbumScreenRoute
import com.example.task_1_compose.ui.components.cards.AlbumCard

@Composable
fun AlbumsList(navController: NavController) {
    val viewModel: AlbumsListViewModel = viewModel()

    val albums by viewModel.albums.collectAsState()

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
