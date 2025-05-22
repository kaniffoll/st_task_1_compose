package com.example.task_1_compose.users.user_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.task_1_compose.R
import com.example.task_1_compose.data.User
import com.example.task_1_compose.data.usersList

@Composable
fun UserCard(
    user: User,
    modifier: Modifier,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .fillMaxWidth(),
        shape = RectangleShape,
        border = BorderStroke(
            dimensionResource(R.dimen.border_stroke_3),
            Color.Black
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
                .spacedBy(dimensionResource(R.dimen.padding_small)),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            UserAndUsername(user)
            Text(
                text = stringResource(R.string.address) + user.address,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_small)
                ),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.number) + user.phone,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_small)
                )
            )
            Spacer(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    UserCard(
        user = usersList[0],
        modifier = Modifier
    ) {}
}