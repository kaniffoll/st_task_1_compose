package com.example.task_1_compose.components.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R

@Composable
fun UserImageAndName(username: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement
            .spacedBy(
                dimensionResource(R.dimen.padding_small_2)
            ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Avatar(
            username,
            dimensionResource(R.dimen.avatar_small)
        )
        Text(
            text = username,
            fontSize = dimensionResource(R.dimen.text_standard).value.sp
        )
    }
}
