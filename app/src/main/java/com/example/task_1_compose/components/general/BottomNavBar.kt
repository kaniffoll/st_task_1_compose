package com.example.task_1_compose.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.R
import com.example.task_1_compose.data.navBarItems

@Composable
fun BottomNavBar(
    navController: NavController,
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar(containerColor = Color.White) {
        navBarItems.forEachIndexed { index, item ->
            NavigationBarItem(
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.padding_small)),
                selected = selectedItemIndex == index,
                icon = {
                    Box(
                        modifier = Modifier
                            .padding(bottom = dimensionResource(R.dimen.padding_mini_2))
                            .size(dimensionResource(R.dimen.navbar_item))
                            .background(
                                color = colorResource(item.color),
                                shape = CircleShape
                            )
                    )
                },
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.appRoute)
                },
                label = {
                    Text(stringResource(item.title))
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(navController = rememberNavController())
}