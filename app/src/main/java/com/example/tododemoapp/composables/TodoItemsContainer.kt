package com.example.tododemoapp.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tododemoapp.data.TodoItem
import com.example.tododemoapp.ui.constants.MediumDp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun TodoItemsContainer(
    modifier: Modifier = Modifier,
    todoItemsFlow: Flow<List<TodoItem>> = flowOf(listOf()),
    overlappingElementsHeight: Dp = 0.dp
) {
    val todos = todoItemsFlow.collectAsState(initial = listOf()).value
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        items(todos, key = { it.id }) { item ->
            TodoItemUi(
                todoItem = item
            )
        }
        item { Spacer(modifier = Modifier.height(overlappingElementsHeight)) }
    }
}

@Preview
@Composable
fun TodoItemsContainerPreview() {
    TodoItemsContainer(
        todoItemsFlow = flowOf(
            listOf(
                TodoItem(title = "Todo Item 2"),
                TodoItem(title = "Todo Item 3"),
            )
        )
    )
}