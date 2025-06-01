package com.example.task_1_compose.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.PostCard
import com.example.task_1_compose.navigation.PostScreenRoute
import com.example.task_1_compose.repositories.PostRepository
import com.example.task_1_compose.viewmodels.PostViewModel
import kotlinx.coroutines.delay

@Composable
fun PostList(navController: NavController) {
    val postViewModel: PostViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PostViewModel(PostRepository()) as T
            }
        }
    )

    val lazyListState = rememberLazyListState()
    val posts by postViewModel.postsState.collectAsState()
    val isAtBottom by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom) {
            delay(1000L) //очевидно стоит убрать, нужно просто для демонстрации работы подгрузки
            postViewModel.loadMorePosts()
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .background(color = Color.White)
    ) {
        items(posts) {
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