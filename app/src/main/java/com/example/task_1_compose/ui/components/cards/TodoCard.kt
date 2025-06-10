package com.example.task_1_compose.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.domain.data.dataclasses.Todo

@Composable
fun TodosCard(
    todo: Todo,
    onTextChangeById: (Int, String) -> Unit,
    removeAtIndex: (Int) -> Unit
) {
    OutlinedCustomCard {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = todo.text,
                onValueChange = { onTextChangeById(todo.id, it) },
                modifier = Modifier
                    .weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(R.color.text_field_unfocused),
                    focusedTextColor = Color.Black,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontSize = dimensionResource(R.dimen.text_standard).value.sp
                )
            )
            Icon(
                Icons.Rounded.Check,
                contentDescription = stringResource(R.string.done_icon),
                modifier = Modifier
                    .size(dimensionResource(R.dimen.check_size))
                    .clickable {
                        removeAtIndex(todo.id)
                    }
                    .padding(
                        end = dimensionResource(R.dimen.padding_small)
                    ),
                tint = Color.Black
            )
        }
    }
}
