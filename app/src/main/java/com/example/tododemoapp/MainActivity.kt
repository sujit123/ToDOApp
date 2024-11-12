package com.example.tododemoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.room.Room
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.tododemoapp.composables.TodoItemsContainer
import com.example.tododemoapp.data.AppDatabase
import com.example.tododemoapp.data.TodoRepository
import com.example.tododemoapp.ui.constants.OverlappingHeight
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "todo-db")
            .build()
        val mainViewModel =
            MainViewModel(TodoRepository(db.todoDao()), ioDispatcher = Dispatchers.IO)
        setContent {
            val ctx = LocalContext.current
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Row {
                                Text("Auto Hide or Extend Fab")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                        ),
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        ctx.startActivity(
                            Intent(
                                ctx,
                                SecondActivity::class.java
                            )
                        )
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                },
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    ) {
                        Text(
                            "Press the + button to add a TODO item", modifier = Modifier
                                .align(
                                    Alignment.TopCenter
                                )
                                .padding(5.dp)
                        )
                        TodoItemsContainer(
                            todoItemsFlow = mainViewModel.todos,
                            overlappingElementsHeight = OverlappingHeight
                        )
                    }
                },
            )
        }
    }
}

