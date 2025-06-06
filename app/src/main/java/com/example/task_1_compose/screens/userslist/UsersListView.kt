package com.example.task_1_compose.screens.userslist

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.OutlinedCustomCard
import com.example.task_1_compose.components.general.Avatar
import com.example.task_1_compose.data.dataclasses.User
import com.example.task_1_compose.navigation.UserScreenRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UsersList(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val usersViewModel: UsersViewModel = viewModel()
    val users by usersViewModel.usersState.collectAsState()
    LazyColumn(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        items(users) {
            UserCard(
                user = it,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            ) { navController.navigate(UserScreenRoute(it)) }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserCard(
    user: User,
    modifier: Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClick: () -> Unit
) {
    OutlinedCustomCard(
        onClick = onClick,
        modifier = modifier
    ) {
        UserCardHeader(
            user, sharedTransitionScope, animatedContentScope
        )
        Text(
            text = stringResource(R.string.address) + " " + user.address,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small)
            ),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.number) + " " + user.phone,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small)
            )
        )
        Spacer(modifier = Modifier)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserCardHeader(
    user: User,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.padding_mini_2)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        with(sharedTransitionScope) {
            Avatar(
                user.name,
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image${user.username}"),
                        animatedVisibilityScope = animatedContentScope
                    ),
                size = dimensionResource(R.dimen.avatar_small)
            )
        }
        Column {
            with(sharedTransitionScope) {
                Text(
                    text = user.name,
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "text${user.username}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                        .fillMaxWidth()
                )
            }
            Text(text = user.username)
        }
    }
}
