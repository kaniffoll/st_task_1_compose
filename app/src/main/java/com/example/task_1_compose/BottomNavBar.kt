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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController){
    BottomAppBar(
        containerColor = Color.White,
        modifier = Modifier.height(120.dp)
    ){
        Row(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .size(42.dp)
                        .background(
                            color = Color(color = 0xFF00a3f9),
                            shape = RoundedCornerShape(100)
                        )
                        .clickable {
                            navController.navigate(Screen.PostList.name)
                        }
                )
                Text(
                    text = "Posts",
                    fontSize = 17.sp
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .size(42.dp)
                        .background(
                            color = Color(color = 0xFFffae36),
                            shape = RoundedCornerShape(100)
                        )
                        .clickable {
                            navController.navigate(Screen.AlbumsList.name)
                        }
                )
                Text(
                    text = "Photos",
                    fontSize = 17.sp
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .size(42.dp)
                        .background(
                            color = Color(color = 0xFFff241a),
                            shape = RoundedCornerShape(100)
                        )
                        .clickable {
                            navController.navigate(Screen.TodosList.name)
                        }
                )
                Text(
                    text = "Todos",
                    fontSize = 17.sp
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .size(42.dp)
                        .background(
                            color = Color(color = 0xFF00ab90),
                            shape = RoundedCornerShape(100)
                        )
                        .clickable {
                            navController.navigate(Screen.UsersList.name)
                        }
                )
                Text(
                    text = "Users",
                    fontSize = 17.sp
                )
            }
        }
    }
}