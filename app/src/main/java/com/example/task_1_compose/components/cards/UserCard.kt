package com.example.task_1_compose.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.task_1_compose.R
import com.example.task_1_compose.components.general.Avatar
import com.example.task_1_compose.data.dataclasses.User
import com.example.task_1_compose.data.usersList

@Composable
fun UserCard(
    user: User,
    modifier: Modifier,
    onClick: () -> Unit
) {
    OutlinedCustomCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(R.dimen.padding_mini_2)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement
                .spacedBy(dimensionResource(R.dimen.padding_small)),
        ) {
            Avatar(
                user.name,
                dimensionResource(R.dimen.avatar_small)
            )
            Column {
                Text(text = user.name)
                Text(text = user.username)
            }
        }
        Text(
            text = stringResource(R.string.address) + " " + user.address,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small)
            ),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.number) + " " + user.phone,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small)
            )
        )
        Spacer(modifier = Modifier)
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