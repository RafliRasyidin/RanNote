package com.rasyidin.rannote.feature_todo.data.source.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rasyidin.rannote.feature_note.data.source.local.entity.todo.TodoEntity
import javax.inject.Inject

class TodoLocalDataSource @Inject constructor(private val toDoDao: ToDoDao) {

    fun getTodos() = Pager(
        config = PagingConfig(10)
    ) {
        toDoDao.getAllTodo()
    }

    suspend fun saveTask(todoEntity: TodoEntity) = toDoDao.saveTask(todoEntity)

    suspend fun deleteTask(todoEntity: TodoEntity) = toDoDao.deleteTask(todoEntity)
}