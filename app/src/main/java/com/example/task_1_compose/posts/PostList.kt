package com.example.task_1_compose.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.components.BottomNavBar
import com.example.task_1_compose.R
import com.example.task_1_compose.data.PostScreenRoute
import com.example.task_1_compose.data.postsList
import com.example.task_1_compose.posts.posts_components.PostCard

@Composable
fun PostList(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            Box(modifier = Modifier.height(dimensionResource(R.dimen.top_appbar_height)))
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
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.White)
        ) {
            items(postsList) {
                PostCard(
                    post = it,
                    modifier = Modifier
                        .padding(
                            bottom = dimensionResource(R.dimen.padding_small_2)
                        )
                ) { navController.navigate(PostScreenRoute(it)) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostListPreview() {
    PostList(navController = rememberNavController())
}