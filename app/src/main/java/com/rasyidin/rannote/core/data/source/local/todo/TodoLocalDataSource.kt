package com.rasyidin.rannote.core.data.source.local.todo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rasyidin.rannote.core.data.source.local.entity.todo.TodoEntity
import com.rasyidin.rannote.core.data.source.local.room.ToDoDao
import javax.inject.Inject

class TodoLocalDataSource @Inject constructor(private val toDoDao: ToDoDao) {

    fun getAllTodo() = Pager(
        config = PagingConfig(10)
    ) {
        toDoDao.getAllTodo()
    }

    suspend fun saveTask(todoEntity: TodoEntity) = toDoDao.saveTask(todoEntity)

    suspend fun deleteTask(todoEntity: TodoEntity) = toDoDao.deleteTask(todoEntity)
}