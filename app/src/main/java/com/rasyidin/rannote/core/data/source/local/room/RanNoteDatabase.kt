package com.rasyidin.rannote.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity
import com.rasyidin.rannote.core.data.source.local.entity.todo.TodoEntity
import com.rasyidin.rannote.core.data.source.local.room.converter.CategoryConverters
import com.rasyidin.rannote.core.data.source.local.room.converter.PriorityConverter
import com.rasyidin.rannote.core.data.source.local.room.converter.RemindMeConverter
import com.rasyidin.rannote.core.data.source.local.room.dao.NoteDao
import com.rasyidin.rannote.core.data.source.local.room.dao.ToDoDao

@Database(entities = [NoteEntity::class, TodoEntity::class], version = 1)
@TypeConverters(CategoryConverters::class, RemindMeConverter::class, PriorityConverter::class)
abstract class RanNoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getTodoDao(): ToDoDao
}