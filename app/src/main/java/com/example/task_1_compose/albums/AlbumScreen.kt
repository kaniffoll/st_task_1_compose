package com.example.task_1_compose.albums

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.task_1_compose.BottomNavBar
import com.example.task_1_compose.data.albumsList

@Composable
fun AlbumScreen(
    index: Int,
    navController: NavController
) {
    var showFullScreen by remember { mutableStateOf(false) }
    var currentIndex by remember { mutableIntStateOf(0) }

    if (showFullScreen) {
        // TODO: такую вьюшку лучше открывать отдельным экраном
        ImagePager(
            albumIndex = index,
            initialImage = currentIndex,
            onClose = { showFullScreen = false }
        )
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            Box(modifier = Modifier.height(100.dp))
        },
        bottomBar = {
            BottomNavBar(navController)
            HorizontalDivider(
                color = Color.Black,
                thickness = 3.dp
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            items(albumsList[index].photos.size) { id ->
                PhotoCard(
                    modifier = Modifier,
                    index = id,
                    albumIndex = index,
                    onClick = { value ->
                        currentIndex = value
                        showFullScreen = true
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AlbumPreview(){
//    AlbumScreen(index = 0)
//}