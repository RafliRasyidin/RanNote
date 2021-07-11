package com.rasyidin.rannote.core.data.source.local.room

import androidx.room.*
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE title LIKE :query OR description LIKE :query")
    fun searchNoteByTitleOrDesc(query: String): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteEntity)
}