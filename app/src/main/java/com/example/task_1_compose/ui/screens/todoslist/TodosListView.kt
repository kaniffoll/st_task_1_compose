package com.example.task_1_compose.ui.screens.todoslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.cards.TodosCard
import com.example.task_1_compose.ui.components.containers.RemoveFocusContainer

@Composable
fun TodosList() {
    val viewModel: TodosListViewModel = viewModel()

    val todos by viewModel.todos.collectAsState()

    RemoveFocusContainer {
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
                        viewModel.updateText(id, text)
                    }
                ) { viewModel.removeTodoByIndex(todo.id) }
            }
        }

        TodosFab {
            viewModel.addTodo()
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
