package com.rasyidin.rannote.feature_todo.data.source.local

import androidx.paging.PagingSource
import androidx.room.*
import com.rasyidin.rannote.feature_note.data.source.local.entity.todo.TodoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun getAllTodo(): PagingSource<Int, TodoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(todoEntity: TodoEntity)

    @Delete
    suspend fun deleteTask(todoEntity: TodoEntity)
}