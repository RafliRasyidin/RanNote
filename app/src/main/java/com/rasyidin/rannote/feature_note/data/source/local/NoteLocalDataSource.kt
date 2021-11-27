package com.rasyidin.rannote.feature_note.data.source.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.sqlite.db.SupportSQLiteQuery
import com.rasyidin.rannote.feature_note.data.source.local.entity.note.NoteEntity
import javax.inject.Inject

class NoteLocalDataSource @Inject constructor(
    private val noteDao: NoteDao
) {

    private val pagingConfig = PagingConfig(10)

    fun getNotes(query: SupportSQLiteQuery) = Pager(
        config = pagingConfig
    ) {
        noteDao.getNotes(query)
    }.flow


    fun searchNoteByTitleOrDesc(query: String) = Pager(
        config = pagingConfig
    ) {
        noteDao.searchNoteByTitleOrDesc(query)
    }.flow

    suspend fun saveNote(note: NoteEntity) = noteDao.saveNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

}