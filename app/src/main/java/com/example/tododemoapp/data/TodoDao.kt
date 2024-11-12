package com.example.tododemoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoItem")
    fun getAllTodos(): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoItem)
}