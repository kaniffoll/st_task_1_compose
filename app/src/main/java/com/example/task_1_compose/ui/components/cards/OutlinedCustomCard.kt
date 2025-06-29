package com.example.task_1_compose.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.task_1_compose.R

@Composable
fun OutlinedCustomCard(
    modifier: Modifier = Modifier,
    itemsSpacedBy: Dp = dimensionResource(R.dimen.padding_large),
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .fillMaxWidth(),
        shape = RectangleShape,
        border = BorderStroke(
            width = dimensionResource(R.dimen.border_stroke_3),
            color = Color.Black
        ),
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White,
        )
    ) {
        Column(
            verticalArrangement = Arrangement
                .spacedBy(
                    itemsSpacedBy
                ),
            modifier = Modifier
                .padding(
                    dimensionResource(R.dimen.padding_small),
                )
        ) {
            content()
        }
    }
}