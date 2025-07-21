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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.SuccessData
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.UserScreenRoute
import com.example.task_1_compose.ui.components.cards.UserCard
import com.example.task_1_compose.ui.components.general.LoadingIndicator
import com.example.task_1_compose.ui.components.views.buttons.ErrorButton
import com.example.task_1_compose.ui.screens.userslist.store.UsersListIntent
import com.example.task_1_compose.ui.screens.userslist.store.UsersListStoreFactory

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UsersList(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val storeFactory = UsersListStoreFactory(DefaultStoreFactory(), LocalContext.current)

    val store = remember { storeFactory.create() }

    val state = store.states.collectAsState(initial = store.state)

    LazyColumn(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        when (state.value.statefulData) {
            is LoadingData -> {
                item {
                    LoadingIndicator()
                }
            }

            is ErrorData -> {
                item {
                    ErrorButton {
                        store.accept(UsersListIntent.LoadUsers)
                    }
                }
            }

            is SuccessData -> {
                val currentUsers = state.value.currentUsers
                items(currentUsers) {
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
    }
}
