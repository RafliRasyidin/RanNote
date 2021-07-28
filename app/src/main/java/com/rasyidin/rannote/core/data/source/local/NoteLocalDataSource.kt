package com.rasyidin.rannote.core.data.source.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity
import com.rasyidin.rannote.core.data.source.local.room.NoteDao
import javax.inject.Inject

class NoteLocalDataSource @Inject constructor(private val noteDao: NoteDao) {

    fun getAllNotesOrderByDateDesc() = Pager(
        config = PagingConfig(10)
    ) {
        noteDao.getAllNotesOrderByDateDesc()
    }

    fun getAllNotesOrderByDateAsc() = Pager(
        config = PagingConfig(10)
    ) {
        noteDao.getAllNotesOrderByDateAsc()
    }

    fun getAllNotesOrderByTitleAsc() = Pager(
        config = PagingConfig(10)
    ) {
        noteDao.getAllNotesOrderByTitleAsc()
    }

    fun getAllNotesOrderByTitleDesc() = Pager(
        config = PagingConfig(10)
    ) {
        noteDao.getAllNotesOrderByTitleDesc()
    }

    fun searchNoteByTitleOrDesc(query: String) = Pager(
        config = PagingConfig(10)
    ) {
        noteDao.searchNoteByTitleOrDesc(query)
    }

    suspend fun saveNote(note: NoteEntity) = noteDao.saveNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

}