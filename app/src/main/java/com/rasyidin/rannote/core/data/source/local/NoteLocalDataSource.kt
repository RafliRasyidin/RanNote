package com.rasyidin.rannote.core.data.source.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity
import com.rasyidin.rannote.core.data.source.local.room.NoteDao
import javax.inject.Inject

class NoteLocalDataSource @Inject constructor(private val noteDao: NoteDao) {

    fun getAllNotes() = Pager(
        config = PagingConfig(10)
    ) {
        NotePagingSource(noteDao)
    }

    fun searchNoteByTitleOrDesc(query: String) = noteDao.searchNoteByTitleOrDesc(query)

    suspend fun saveNote(note: NoteEntity) = noteDao.saveNote(note)

    suspend fun updateNote(note: NoteEntity) = noteDao.updateNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

}