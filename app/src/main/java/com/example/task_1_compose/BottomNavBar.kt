package com.example.task_1_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.task_1_compose.components.NavBarItem
import com.example.task_1_compose.data.AlbumsListRoute
import com.example.task_1_compose.data.PostListRoute
import com.example.task_1_compose.data.TodosListRoute
import com.example.task_1_compose.data.UsersListRoute
import com.example.task_1_compose.posts.PostList

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
                title = stringResource(R.string.posts)
            )
            NavBarItem(
                navController = navController,
                appRoute = AlbumsListRoute,
                title = stringResource(R.string.photos)
            )
            NavBarItem(
                navController = navController,
                appRoute = TodosListRoute,
                title = stringResource(R.string.todos)
            )
            NavBarItem(
                navController = navController,
                appRoute = UsersListRoute,
                title = stringResource(R.string.users)
            )
        }
    }
}