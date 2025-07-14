package com.example.task_1_compose.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.EmptyBottomBars
import com.example.task_1_compose.navigation.ImagePagerRoute
import com.example.task_1_compose.navigation.Navigation
import com.example.task_1_compose.ui.components.appbars.TopBar
import com.example.task_1_compose.ui.screens.home.components.BottomNavigationBar

@Composable
fun HomePage() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination.toString()

    Scaffold(
        containerColor = if (!currentRoute.contains(ImagePagerRoute::class.simpleName.toString())) {
            Color.White
        } else {
            Color.Black
        },
        topBar = {
            TopBar(navController)
        },
        bottomBar = {
            if (EmptyBottomBars.none{currentRoute.contains(it)}) {
                BottomNavigationBar(navController)
                HorizontalDivider(
                    color = Color.Black,
                    thickness = dimensionResource(R.dimen.border_stroke_3)
                )
            }
        }
    ) { innerPadding ->
        Navigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
