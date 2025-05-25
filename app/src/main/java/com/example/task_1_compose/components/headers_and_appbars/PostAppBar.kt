package com.example.task_1_compose.components.headers_and_appbars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.example.task_1_compose.R

@Composable
fun PostAppBar(navController: NavController) {
    var isLikeClicked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .height(dimensionResource(R.dimen.top_appbar_height))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
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
        Icon(
            if (isLikeClicked) {
                Icons.Rounded.Favorite
            } else {
                Icons.Rounded.FavoriteBorder
            },
            contentDescription = null,
            modifier = Modifier
                .padding(
                    end = dimensionResource(R.dimen.padding_medium),
                    bottom = dimensionResource(R.dimen.padding_mini)
                )
                .size(dimensionResource(R.dimen.heart_size))
                .clickable { isLikeClicked = !isLikeClicked }
        )
    }
}

