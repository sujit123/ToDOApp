package com.example.tododemoapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tododemoapp.data.TodoItem
import com.example.tododemoapp.ui.constants.*

@Composable
fun TodoItemUi(
    todoItem: TodoItem = TodoItem(title = "Todo Item"),
) {
    val backgroundColor = TodoItemBackgroundColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(TodoItemHeight),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = todoItem.title,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                style = TodoItemTitleTextStyle.copy(color = TodoItemTextColor),
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun TodoItemUiPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        TodoItemUi(todoItem = TodoItem(title = "Todo 1"))
        TodoItemUi(todoItem = TodoItem(title = "Todo 2"))
    }
}
