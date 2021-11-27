package com.rasyidin.rannote.feature_note.data.source.local

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.rasyidin.rannote.feature_note.data.source.local.entity.note.NoteEntity

@Dao
interface NoteDao {

    @RawQuery(observedEntities = [NoteEntity::class])
    fun getNotes(query: SupportSQLiteQuery): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM note WHERE title LIKE :query OR description LIKE :query")
    fun searchNoteByTitleOrDesc(query: String): PagingSource<Int, NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

}