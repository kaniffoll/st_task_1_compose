package com.example.task_1_compose.albums

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.BottomNavBar
import com.example.task_1_compose.R
import com.example.task_1_compose.Screen
import com.example.task_1_compose.components.TopBar
import com.example.task_1_compose.data.albumsList

@Composable
fun AlbumScreen(
    index: Int,
    navController: NavController
) {
    var currentIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopBar(navController)
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
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
        ) {
            items(albumsList[index].photos.size) { id ->
                PhotoCard(
                    modifier = Modifier,
                    index = id,
                    albumIndex = index,
                    onClick = {
                        currentIndex = id
                        navController.navigate(Screen.ImagePager.name + "/$index" + "/$currentIndex")
                    }
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(
                            dimensionResource(R.dimen.padding_large)
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumPreview() {
    AlbumScreen(index = 0, navController = rememberNavController())
}