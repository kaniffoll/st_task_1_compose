package com.example.task_1_compose.todos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.BottomNavBar

@Composable
fun TodosList(
    navController: NavController
) {
    val todos = remember { mutableStateListOf<String>() }
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(todos.size) { index ->
                    Row(
                        modifier = Modifier.border(BorderStroke(2.dp, Color.Black)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = todos[index],
                            onValueChange = { todos[index] = it },
                            modifier = Modifier.padding(10.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent
                            ),
                            textStyle = TextStyle(
                                fontSize = 25.sp
                            )
                        )
                        Icon(
                            Icons.Rounded.Check,
                            contentDescription = "done",
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    todos.removeAt(index)
                                }
                                .padding(end = 10.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 20.dp, end = 20.dp)
                        .background(
                            color = Color(0xFF00a3f9),
                            shape = RoundedCornerShape(100)
                        )
                        .size(70.dp)
                        .clickable {
                            todos.add("")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        fontSize = 40.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTodoList() {
    TodosList(navController = rememberNavController())
}