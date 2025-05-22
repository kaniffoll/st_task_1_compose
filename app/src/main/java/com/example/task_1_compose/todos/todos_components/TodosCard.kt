package com.example.task_1_compose.todos.todos_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R

@Composable
fun TodosCard(
    index: Int,
    todos: SnapshotStateList<String>
) {
    Row(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .border(
            BorderStroke(
                width = dimensionResource(R.dimen.border_stroke_2),
                color = Color.Black
            )
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = todos[index],
            onValueChange = { todos[index] = it },
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_mini_2))
                .weight(1f),
            colors = OutlinedTextFieldDefaults.colors(
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
            contentDescription = "done",
            modifier = Modifier
                .size(dimensionResource(R.dimen.check_size))
                .clickable {
                    todos.removeAt(index)
                }
                .padding(
                    end = dimensionResource(R.dimen.padding_small)
                ),
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodosCardPreview() {
    TodosCard(0, todos = mutableStateListOf(""))
}