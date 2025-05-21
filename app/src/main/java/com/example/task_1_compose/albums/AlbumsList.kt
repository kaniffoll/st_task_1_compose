package com.example.task_1_compose.albums

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.BottomNavBar
import com.example.task_1_compose.R
import com.example.task_1_compose.Screen
import com.example.task_1_compose.components.AlbumCard
import com.example.task_1_compose.data.albumsList

@Composable
fun AlbumsList(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            Box(
                modifier = Modifier
                    .height(
                        dimensionResource(R.dimen.top_appbar_height)
                    )
            )
        },
        bottomBar = {
            BottomNavBar(navController)
            HorizontalDivider(
                color = Color.Black,
                thickness = dimensionResource(R.dimen.border_stroke_3)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement
                .spacedBy(
                    dimensionResource(R.dimen.padding_small)
                )
        ) {
            items(albumsList.size) { index ->
                AlbumCard(
                    index = index
                ) {
                    navController.navigate(Screen.AlbumScreen.name + "/$index")
                }
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