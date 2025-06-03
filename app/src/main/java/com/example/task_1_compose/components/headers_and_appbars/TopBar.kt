package com.example.task_1_compose.components.headers_and_appbars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.task_1_compose.R
import com.example.task_1_compose.data.EmptyTopBars
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.navigation.ImagePagerRoute
import com.example.task_1_compose.navigation.PostScreenRoute
import com.example.task_1_compose.navigation.SplashScreenRoute
import com.example.task_1_compose.repositories.PostRepository
import kotlinx.serialization.json.Json
import java.net.URLDecoder

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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .height(dimensionResource(R.dimen.top_appbar_height))
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium)
                    ),
                contentAlignment = Alignment.BottomStart
            ) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.back_icon_size))
                        .clickable { navController.popBackStack() },
                )
            }
        }

        else -> {
            Box(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.top_appbar_height))
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_mini)
                        )
                        .size(dimensionResource(R.dimen.back_icon_size))
                        .clickable { navController.popBackStack() }
                )
            }
        }
    }
}