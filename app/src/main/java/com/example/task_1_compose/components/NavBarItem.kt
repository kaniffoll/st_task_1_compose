package com.example.task_1_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.task_1_compose.R
import com.example.task_1_compose.data.AppRoute


@Composable
fun NavBarItem(
    navController: NavController,
    appRoute: AppRoute,
    title: String,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_mini_2))
                .size(dimensionResource(R.dimen.navbar_item))
                .background(
                    color = color,
                    shape = CircleShape
                )
                .clickable {
                    navController.navigate(appRoute)
                }
        )
        Text(
            text = title,
            fontSize = dimensionResource(R.dimen.padding_small_2).value.sp
        )
    }
}