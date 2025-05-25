package com.example.task_1_compose.components.fabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R

@Composable
fun TodosFab(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { onClick() },
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(R.dimen.padding_medium_2),
                    end = dimensionResource(R.dimen.padding_medium_2)
                )
                .size(dimensionResource(R.dimen.fab_size)),
            containerColor = Color(0xFF00a3f9),
            contentColor = Color.White,
            shape = CircleShape

        ) {
            Text(
                text = stringResource(R.string.plus),
                fontSize = dimensionResource(R.dimen.text_large).value.sp,
                color = Color.White
            )
        }
    }
}