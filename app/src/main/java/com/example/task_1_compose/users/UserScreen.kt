package com.example.task_1_compose.users

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.components.BottomNavBar
import com.example.task_1_compose.R
import com.example.task_1_compose.components.TopBar
import com.example.task_1_compose.components.comments.UserComments
import com.example.task_1_compose.data.User
import com.example.task_1_compose.data.usersList

@Composable
fun UserScreen(
    user: User,
    navController: NavController
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopBar(navController)
        },
        bottomBar = {
            BottomNavBar(navController)
            HorizontalDivider(
                color = Color.Black,
                thickness = dimensionResource(R.dimen.border_stroke_3)
            )
        }
    ) { innerPadding ->
        UserComments(user,Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun UserPreview() {
    UserScreen(
        user = usersList[1],
        navController = rememberNavController()
    )
}