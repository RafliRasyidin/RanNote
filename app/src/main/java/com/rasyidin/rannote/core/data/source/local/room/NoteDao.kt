package com.rasyidin.rannote.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.*
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM note ORDER BY date DESC")
    fun getAllNotes(): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM note WHERE :query LIKE title OR :query LIKE description")
    fun searchNoteByTitleOrDesc(query: String): PagingSource<Int, NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

}