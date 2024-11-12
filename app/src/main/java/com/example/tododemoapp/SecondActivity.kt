package com.example.tododemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.tododemoapp.composables.TodoInputBar
import com.example.tododemoapp.data.AppDatabase
import com.example.tododemoapp.data.TodoRepository
import kotlinx.coroutines.Dispatchers

class SecondActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "todo-db")
            .build()
        val mainViewModel =
            MainViewModel(TodoRepository(db.todoDao()), ioDispatcher = Dispatchers.IO)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Row {
                                Text("Enter new item")
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
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    ) {
                        TodoInputBar(
                            modifier = Modifier.align(Alignment.BottomStart),
                            onAddButtonClick = mainViewModel::addTodo
                        )
                    }
                },
            )
        }
    }
}