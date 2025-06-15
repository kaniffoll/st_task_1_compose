package com.example.task_1_compose.ui.screens.userslist

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.UserScreenRoute
import com.example.task_1_compose.ui.components.cards.UserCard

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UsersList(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val viewModel: UsersViewModel = viewModel()

    val users by viewModel.users.collectAsState()

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
            ) {
                navController.navigate(UserScreenRoute(it))
            }
        }
    }
}
