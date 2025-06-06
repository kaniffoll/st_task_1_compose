package com.example.task_1_compose.screens.todoslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.OutlinedCustomCard
import com.example.task_1_compose.data.dataclasses.Todo

@Composable
fun TodosList() {
    val todosListViewModel: TodosListViewModel = viewModel()
    val todos by todosListViewModel.todosState.collectAsState()
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                focusManager.clearFocus()
            }
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(
                    dimensionResource(R.dimen.padding_medium_2)
                )
        ) {
            items(todos) { todo ->
                TodosCard(
                    todo,
                    onTextChangeById = { id, text ->
                        todosListViewModel.updateText(id, text)
                    }
                ) { todosListViewModel.removeTodoByIndex(todo.id) }
            }
        }
        TodosFab {
            todosListViewModel.addTodo()
        }
    }
}

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

@Composable
fun TodosCard(
    todo: Todo,
    onTextChangeById: (Int, String) -> Unit,
    removeAtIndex: (Int) -> Unit
) {
    OutlinedCustomCard() {
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

@Preview(showBackground = true)
@Composable
fun TodosCardPreview() {
    TodosCard(
        Todo(id = 0, text = "text"),
        { _, _ -> }
    ) {}
}

@Preview
@Composable
fun PreviewTodoList() {
    TodosList()
}