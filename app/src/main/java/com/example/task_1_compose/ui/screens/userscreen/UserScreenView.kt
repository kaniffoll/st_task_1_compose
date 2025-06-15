package com.example.task_1_compose.ui.screens.userscreen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.data.dataclasses.User
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.containers.CommentsSection
import com.example.task_1_compose.ui.components.general.Avatar
import com.example.task_1_compose.utilities.getRandomColorByUsername

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserScreen(
    user: User,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val viewModel: UserScreenViewModel = viewModel { UserScreenViewModel(user) }

    val currentUser by viewModel.user.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()
    val loadingError by viewModel.loadingError.collectAsState()
    val canLoadMore by viewModel.canLoadMore.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            UserHeader(
                currentUser,
                sharedTransitionScope,
                animatedContentScope
            )
        }
        item {
            CommentsSection(
                comments = currentUser.comments,
                modifier = Modifier.fillParentMaxSize(),
                loadingError = loadingError,
                isLoading = isLoading,
                canLoadMore = canLoadMore
            ) { viewModel.loadComments() }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserHeader(
    user: User,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        UserHeaderAvatar(
            user,
            sharedTransitionScope,
            animatedContentScope
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.spacer_header_height)))
        with(sharedTransitionScope) {
            Text(
                text = user.name,
                fontSize = dimensionResource(R.dimen.text_large).value.sp,
                modifier = Modifier.sharedElement(
                    rememberSharedContentState(key = "text${user.username}"),
                    animatedVisibilityScope = animatedContentScope
                )
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserHeaderAvatar(
    user: User,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val randomColor = remember {
        getRandomColorByUsername(user.username)
    }

    val spacerHeight = dimensionResource(R.dimen.spacer_header_height)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.color_header_height)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .border(dimensionResource(R.dimen.border_stroke_2), Color.Black)
                .background(color = randomColor)
        )
        with(sharedTransitionScope) {
            Avatar(
                username = user.name,
                size = dimensionResource(R.dimen.avatar_large),
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image${user.username}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .graphicsLayer {
                        clip = true
                        shape = CircleShape
                        translationY = spacerHeight.toPx()
                    },
                fontSize = dimensionResource(R.dimen.text_large_2).value.sp
            )
        }
    }
}
