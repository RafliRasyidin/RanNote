package com.rasyidin.rannote.feature_note.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rasyidin.rannote.feature_note.data.source.local.entity.note.NoteEntity
import com.rasyidin.rannote.feature_note.data.source.local.entity.todo.TodoEntity
import com.rasyidin.rannote.feature_todo.data.source.local.converter.CategoryConverters
import com.rasyidin.rannote.feature_todo.data.source.local.converter.PriorityConverter
import com.rasyidin.rannote.feature_todo.data.source.local.converter.RemindMeConverter
import com.rasyidin.rannote.feature_todo.data.source.local.ToDoDao

@Database(entities = [NoteEntity::class, TodoEntity::class], version = 1)
@TypeConverters(CategoryConverters::class, RemindMeConverter::class, PriorityConverter::class)
abstract class RanNoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getTodoDao(): ToDoDao
}