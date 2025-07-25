package com.example.task_1_compose.ui.screens.postslist

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import com.example.domain.resources.TestTags.POST_CARD_TEST_TAG
import com.example.domain.statefuldata.canLoadMore
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.PostScreenRoute
import com.example.task_1_compose.ui.components.cards.PostCard
import com.example.task_1_compose.ui.components.containers.LoadMoreList
import com.example.task_1_compose.ui.screens.postslist.store.PostsListComponent
import com.example.task_1_compose.ui.screens.postslist.store.PostsListIntent

@Composable
fun PostsList(
    navController: NavController,
    component: PostsListComponent
) {
    val store = component.store

    val state by store.states.collectAsState(initial = store.state)

    val scope = rememberCoroutineScope()

    LoadMoreList(
        onLoadMore = { store.accept(PostsListIntent.LoadNextPosts) },
        isPaginationFinished = { !state.posts.canLoadMore(POSTS_PER_PAGE) },
        scope = scope,
        data = state.posts,
    ) { index, list ->
        val post = list[index]

        PostCard(
            post = post,
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(R.dimen.padding_small_2)
                )
                .testTag(POST_CARD_TEST_TAG),
            onLikeClicked = { postId ->
                store.accept(PostsListIntent.ToggleLike(postId))
            }
        ) {
            navController.navigate(PostScreenRoute(post = post))
        }
    }
}
