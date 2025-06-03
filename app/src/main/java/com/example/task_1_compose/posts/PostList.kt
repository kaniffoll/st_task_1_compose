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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.PostCard
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.navigation.PostScreenRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PostList(
    navController: NavController,
    postsState: StateFlow<List<Post>>,
    onLikeClicked: (Int) -> Unit,
    getId: (Int) -> Unit,
    loadMorePosts: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val posts by postsState.collectAsState()
    val isAtBottom by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom) {
            delay(1000L) //очевидно стоит убрать, нужно просто для демонстрации работы подгрузки
            loadMorePosts()
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
                    ),
                onLikeClicked = onLikeClicked
            ) {
                getId(it.id)
                navController.navigate(PostScreenRoute)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostListPreview() {
    PostList(
        navController = rememberNavController(),
        postsState = MutableStateFlow(emptyList()),
        {},
        {}
    ) {}
}