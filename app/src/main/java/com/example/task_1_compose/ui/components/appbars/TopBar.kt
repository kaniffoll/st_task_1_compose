package com.example.task_1_compose.ui.components.appbars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.task_1_compose.R
import com.example.task_1_compose.navigation.EmptyTopBars
import com.example.task_1_compose.navigation.ImagePagerRoute
import com.example.task_1_compose.navigation.SplashScreenRoute

@Composable
fun TopBar(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = backStackEntry?.destination.toString()
    when {
        EmptyTopBars.any { item -> currentRoute.contains(item) } -> {
            Box(modifier = Modifier.height(dimensionResource(R.dimen.top_appbar_height)))
        }
        currentRoute.contains(SplashScreenRoute::class.simpleName.toString()) -> {}
        currentRoute.contains(ImagePagerRoute::class.simpleName.toString()) -> {
            ImagePagerTopBar(navController)
        }
        else -> {
            DefaultTopBar(navController)
        }
    }
}

