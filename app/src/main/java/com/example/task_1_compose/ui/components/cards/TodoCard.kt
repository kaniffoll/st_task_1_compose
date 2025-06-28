package com.example.task_1_compose.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.domain.data.dataclasses.Todo
import com.example.domain.resources.TestTags.TODO_CARD_TEXT_FIELD
import com.example.task_1_compose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TodosCard(
    todo: Todo,
    onTextChangeById: suspend (Int, String) -> Unit,
    scope: CoroutineScope,
    modifier: Modifier = Modifier,
    removeAtIndex: suspend (Int) -> Unit,
) {
    OutlinedCustomCard(modifier) {
        var localText by remember { mutableStateOf(todo.title) }
        var isFocused by remember { mutableStateOf(false) }
        var hasChanged by remember { mutableStateOf(false) }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TodoTextField(
                text = localText,
                onTextChange = {
                    localText = it
                    hasChanged = true
                },
                onFocusChange = { focused ->
                    isFocused = focused
                    if (!isFocused && hasChanged) {
                        scope.launch {
                            onTextChangeById(todo.id, localText)
                        }
                        hasChanged = false
                    }
                },
                modifier = Modifier.weight(1f).testTag(TODO_CARD_TEXT_FIELD)
            )

            TodoIcon(
                onTextChangeById = onTextChangeById,
                removeAtIndex = removeAtIndex,
                scope = scope,
                todo = todo,
                localText = localText,
                isFocused = isFocused
            )
        }
    }
}

@Composable
fun TodoIcon(
    onTextChangeById: suspend (Int, String) -> Unit,
    removeAtIndex: suspend (Int) -> Unit,
    scope: CoroutineScope,
    todo: Todo,
    localText: String,
    isFocused: Boolean
) {
    Icon(
        imageVector = if (isFocused) Icons.Rounded.Refresh else Icons.Rounded.Check,
        contentDescription = if (isFocused) stringResource(R.string.update_icon) else stringResource(R.string.done_icon),
        modifier = Modifier
            .size(dimensionResource(R.dimen.check_size))
            .clickable {
                scope.launch {
                    if (isFocused) onTextChangeById(todo.id, localText) else removeAtIndex(
                        todo.id
                    )
                }
            }
            .padding(
                end = dimensionResource(R.dimen.padding_small)
            ),
        tint = Color.Black)
}

@Composable
fun TodoTextField(
    text: String, onTextChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier.onFocusChanged { focusState ->
            onFocusChange(focusState.isFocused)
        },
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
}