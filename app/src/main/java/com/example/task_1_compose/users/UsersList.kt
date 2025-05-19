package com.example.task_1_compose.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.BottomNavBar
import com.example.task_1_compose.data.usersList

@Composable
fun UsersList(navController: NavController){
    Scaffold(
        containerColor = Color.White,
        topBar = {
            Box(modifier = Modifier.height(100.dp))
        },
        bottomBar = {
            BottomNavBar(navController)
            HorizontalDivider(
                color = Color.Black,
                thickness = 3.dp
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).background(color = Color.White)) {
            items(usersList.size){ index ->
                UserCard(index, navController, modifier = Modifier.padding(bottom = 15.dp))
            }
        }
    }
}

@Preview
@Composable
fun UsersPreview(){
    UsersList(navController = rememberNavController())
}