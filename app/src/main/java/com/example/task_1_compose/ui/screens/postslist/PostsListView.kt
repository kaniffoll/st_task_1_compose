package com.example.task_1_compose.ui.screens.postslist

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.PostScreenRoute
import com.example.task_1_compose.ui.components.cards.PostCard
import com.example.task_1_compose.ui.components.containers.LoadMoreList

@Composable
fun PostsList(
    navController: NavController
) {
    val viewModel: PostsListViewModel = viewModel()

    val posts by viewModel.posts.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refreshPost()
    }

    LoadMoreList(
        canLoadMore = viewModel.canLoadMore,
        onLoadMore = { viewModel.loadNextPosts() },
        contentSize = posts.size
    ) { index ->
        val post = posts[index]

        PostCard(
            post = post,
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(R.dimen.padding_small_2)
                ),
            onLikeClicked = { postId ->
                viewModel.toggleLike(postId)
            }
        ) {
            viewModel.saveLastOpenedPostId(post.id)
            navController.navigate(PostScreenRoute(post = post))
        }
    }
}
