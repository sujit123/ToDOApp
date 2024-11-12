package com.example.tododemoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tododemoapp.data.TodoItem
import com.example.tododemoapp.data.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TodoRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val todos = repository.allTodos
    fun addTodo(todo: String) =
        viewModelScope.launch(ioDispatcher) { repository.insert(TodoItem(title = todo)) }
}