package com.rasyidin.rannote.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class RanNoteDatabase : RoomDatabase(){
    abstract fun getNoteDao() : NoteDao
    abstract fun getTodoDao() : ToDoDao
}