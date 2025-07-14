package com.example.task_1_compose.ui.components.appbars

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.task_1_compose.R

@Composable
fun ImagePagerTopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .height(dimensionResource(R.dimen.top_appbar_height))
            .padding(start = dimensionResource(R.dimen.padding_medium)),
        contentAlignment = Alignment.BottomStart
    ) {
        Icon(
            Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = stringResource(R.string.arrow_back_icon),
            tint = Color.White,
            modifier = Modifier
                .size(dimensionResource(R.dimen.back_icon_size))
                .clickable { navController.popBackStack() },
        )
    }
}