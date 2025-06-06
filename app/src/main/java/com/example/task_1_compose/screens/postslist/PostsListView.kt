package com.example.task_1_compose.screens.postslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.OutlinedCustomCard
import com.example.task_1_compose.components.general.UserImageAndName
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.navigation.PostScreenRoute
import kotlinx.coroutines.delay

@Composable
fun PostsList(
    navController: NavController
) {
    val postsListViewModel: PostsListViewModel = viewModel()
    val lazyListState = rememberLazyListState()
    val posts by postsListViewModel.postsState.collectAsState()
    val canLoadMore by postsListViewModel.canLoadMore.collectAsState()
    val isAtBottom by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        postsListViewModel.refreshPost()
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom && canLoadMore) {
            isLoading = true
            delay(1000L) //очевидно стоит убрать, нужно просто для демонстрации работы подгрузки
            postsListViewModel.loadMorePosts()
            isLoading = false
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
                onLikeClicked = { id ->
                    postsListViewModel.toggleLike(id)
                }
            ) {
                postsListViewModel.saveLastOpenedPostId(it.id)
                navController.navigate(PostScreenRoute(post = it))
            }
        }
        item {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(dimensionResource(R.dimen.loader_size))
                    )
                }
            }
        }
    }
}

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier,
    onLikeClicked: (Int) -> Unit,
    onClick: () -> Unit
) {
    OutlinedCustomCard(onClick = onClick, modifier = modifier) {
        UserImageAndName(post.username)
        Text(
            text = post.title,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small)),
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = post.description,
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.padding_small))
                    .weight(1f)
                    .padding(end = dimensionResource(R.dimen.padding_small)),
                fontSize = dimensionResource(R.dimen.text_standard).value.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                if (post.isLiked) {
                    Icons.Rounded.Favorite
                } else {
                    Icons.Rounded.FavoriteBorder
                },
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        end = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_mini)
                    )
                    .size(dimensionResource(R.dimen.heart_size))
                    .clickable { onLikeClicked(post.id) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostCard() {
    PostCard(
        Post(
            id = 32133,
            username = "User 1",
            description = "Sharing complex coroutine use cases from our production environment",
            title = "Title"
        ),
        modifier = Modifier,
        {}
    ) {}
}

@Preview(showBackground = true)
@Composable
fun PostListPreview() {
    PostsList(navController = rememberNavController())
}