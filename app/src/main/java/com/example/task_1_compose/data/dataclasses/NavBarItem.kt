package com.example.task_1_compose.data.dataclasses

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.task_1_compose.navigation.AppRoute

data class NavBarItem(
    @StringRes val title: Int,
    @ColorRes val color: Int,
    val appRoute: AppRoute
    )
