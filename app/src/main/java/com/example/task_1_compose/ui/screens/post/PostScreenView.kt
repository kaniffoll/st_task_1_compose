package com.example.task_1_compose.ui.screens.post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.statefuldata.canLoadMore
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.containers.CommentsSection
import com.example.task_1_compose.ui.components.general.LoadingIndicator
import com.example.task_1_compose.ui.components.general.UserTile
import com.example.task_1_compose.ui.components.views.buttons.LikeButton
import com.example.task_1_compose.ui.screens.post.store.PostScreenComponent
import com.example.task_1_compose.ui.screens.post.store.PostScreenIntent
import com.example.task_1_compose.ui.screens.post.store.PostScreenStore
import kotlinx.coroutines.delay

@Composable
fun PostScreen(
    post: Post,
    component: PostScreenComponent
) {
    val store = component.store

    val state = store.states.collectAsState(initial = store.state)

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        store.accept(PostScreenIntent.InitializePostScreen(post.copy(comments = mutableListOf())))
    }

    LaunchedEffect(state.value.post?.id) {
        //TODO: возможно можно сделать как-то лучше
        delay(100L)
        state.value.post?.let {
            store.accept(PostScreenIntent.LoadNextComments)
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium),
                bottom = dimensionResource(R.dimen.padding_large_2)
            )
            .fillMaxSize()
            .border(
                BorderStroke(
                    dimensionResource(R.dimen.border_stroke_3),
                    Color.Black
                )
            )
            .background(color = Color.White),
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        val currentPost = state.value.post

        if (currentPost == null) {
            item {
                LoadingIndicator()
            }
        } else {
            item {
                PostBody(currentPost) {
                    store.accept(PostScreenIntent.ToggleLike)
                }
            }

            item {
                CommentsSection(
                    comments = state.value.statefulData,
                    modifier = Modifier.fillParentMaxSize(),
                    canLoadMore = { state.value.statefulData.canLoadMore(COMMENTS_PER_PAGE) },
                    scope = scope
                ) {
                    store.accept(PostScreenIntent.LoadNextComments)
                }
            }
        }
    }
}

@Composable
fun PostBody(
    post: Post,
    onLikeClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.padding_small),
                top = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small)
            ),
        verticalArrangement = Arrangement
            .spacedBy(
                dimensionResource(R.dimen.padding_large)
            ),
    ) {
        UserTile(post.userId.toString())
        PostBodyTitle(post)
        PostBodyDescriptionContent(post, onLikeClicked = onLikeClicked)
    }
}

@Composable
fun PostBodyTitle(post: Post) {
    Text(
        text = post.title,
        modifier = Modifier
            .padding(
                dimensionResource(R.dimen.padding_small)
            ),
        fontSize = dimensionResource(R.dimen.text_standard).value.sp
    )
}

@Composable
fun PostBodyDescriptionContent(
    post: Post,
    onLikeClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = post.body,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_small)
            ),
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            LikeButton(status = post.isLiked, onClicked = onLikeClicked)
        }
    }
}

object FakePostScreenComponent : PostScreenComponent {
    override val store: PostScreenStore
        get() = TODO()
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(
        post = Post(
            id = -1,
            userId = 1,
            title = "Title",
            body = "D".repeat(100),
            comments = mutableListOf(
                Comment("", "")
            )
        ),
        FakePostScreenComponent
    )
}