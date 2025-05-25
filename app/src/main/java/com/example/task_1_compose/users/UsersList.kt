package com.example.task_1_compose.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.UserScreenRoute
import com.example.task_1_compose.data.usersList
import com.example.task_1_compose.components.cards.UserCard

@Composable
fun UsersList(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        items(usersList) {
            UserCard(
                it,
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            ) { navController.navigate(UserScreenRoute(it)) }
        }
    }
}

@Preview
@Composable
fun UsersPreview() {
    UsersList(navController = rememberNavController())
}