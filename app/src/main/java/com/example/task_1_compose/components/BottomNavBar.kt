package com.example.task_1_compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.task_1_compose.R
import com.example.task_1_compose.data.AlbumsListRoute
import com.example.task_1_compose.data.PostListRoute
import com.example.task_1_compose.data.TodosListRoute
import com.example.task_1_compose.data.UsersListRoute

@Composable
fun BottomNavBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color.White,
        modifier = Modifier.height(120.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavBarItem(
                navController = navController,
                appRoute = PostListRoute,
                title = stringResource(R.string.posts),
                color = colorResource(R.color.blue_for_posts)
            )
            NavBarItem(
                navController = navController,
                appRoute = AlbumsListRoute,
                title = stringResource(R.string.photos),
                color = colorResource(R.color.orange_for_photos)
            )
            NavBarItem(
                navController = navController,
                appRoute = TodosListRoute,
                title = stringResource(R.string.todos),
                color = colorResource(R.color.red_for_todos)
            )
            NavBarItem(
                navController = navController,
                appRoute = UsersListRoute,
                title = stringResource(R.string.users),
                color = colorResource(R.color.cyan_for_users)
            )
        }
    }
}