package com.example.task_1_compose.ui.screens.postslist

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.domain.resources.TestTags.POST_CARD_TEST_TAG
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.PostScreenRoute
import com.example.task_1_compose.ui.components.cards.PostCard
import com.example.task_1_compose.ui.components.containers.LoadMoreList

@Composable
fun PostsList(
    navController: NavController
) {
    val viewModel: PostsListViewModel = hiltViewModel()

    val posts by viewModel.posts.collectAsState()

    LoadMoreList(
        onLoadMore = { viewModel.loadNextPosts() },
        isPaginationFinished = { !viewModel.canLoadMorePosts() },
        scope = viewModel.viewModelScope,
        data = posts,
    ) { index ->
        val currentPosts = viewModel.currentPosts()
        val post = currentPosts[index]

        PostCard(
            post = post,
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(R.dimen.padding_small_2)
                )
                .testTag(POST_CARD_TEST_TAG),
            onLikeClicked = { postId ->
                viewModel.toggleLike(postId)
            }
        ) {
            navController.navigate(PostScreenRoute(post = post))
        }
    }
}
