package com.example.task_1_compose.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.PostScreenRoute
import com.example.task_1_compose.data.postsList
import com.example.task_1_compose.components.cards.PostCard

@Composable
fun PostList(navController: NavController) {
    LazyColumn(
        modifier = Modifier
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

@Preview(showBackground = true)
@Composable
fun PostListPreview() {
    PostList(navController = rememberNavController())
}