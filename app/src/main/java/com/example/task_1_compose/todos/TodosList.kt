package com.example.task_1_compose.todos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.BottomNavBar
import com.example.task_1_compose.R

@Composable
fun TodosList(
    navController: NavController
) {
    val todos = remember { mutableStateListOf<String>() }
    Scaffold(
        containerColor = Color.White,
        topBar = {
            Box(modifier = Modifier.height(dimensionResource(R.dimen.top_appbar_height)))
        },
        bottomBar = {
            BottomNavBar(navController)
            HorizontalDivider(
                color = Color.Black,
                thickness = dimensionResource(R.dimen.border_stroke_3)
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
                verticalArrangement = Arrangement
                    .spacedBy(
                        dimensionResource(R.dimen.padding_medium_2)
                    )
            ) {
                items(todos.size) { index ->
                    TodosCard(index, todos)
                }
            }
            TodosFab {
                todos.add("")
            }
        }
    }
}

@Preview
@Composable
fun PreviewTodoList() {
    TodosList(navController = rememberNavController())
}