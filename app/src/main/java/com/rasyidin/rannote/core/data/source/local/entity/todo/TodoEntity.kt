package com.rasyidin.rannote.core.data.source.local.entity.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rasyidin.rannote.core.domain.model.todo.Category
import com.rasyidin.rannote.core.domain.model.todo.Priority
import com.rasyidin.rannote.core.domain.model.todo.RemindMe

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    val todoId: Int = 0,

    @ColumnInfo(name = "title_task")
    val titleTask: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "notification")
    val notification: RemindMe = RemindMe.Disable,

    @ColumnInfo(name = "priority")
    val priority: Priority = Priority.Low,

    @ColumnInfo(name = "Category")
    val category: Category = Category.Work
)


