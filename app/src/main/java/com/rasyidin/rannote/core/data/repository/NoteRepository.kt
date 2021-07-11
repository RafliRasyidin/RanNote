package com.rasyidin.rannote.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.rasyidin.rannote.core.data.source.local.NoteLocalDataSource
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.repository.INoteRepository
import com.rasyidin.rannote.core.utils.toNote
import com.rasyidin.rannote.core.utils.toNoteEntity
import com.rasyidin.rannote.core.utils.toNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteLocalDataSource: NoteLocalDataSource,
) : INoteRepository {

    override fun getAllNotes(): Flow<PagingData<Note>> {
        return noteLocalDataSource.getAllNotes().flow.map { pagingData ->
            pagingData.map {
                it.toNote()
            }
        }
    }

    override fun searchNoteByTitleOrDesc(query: String): Flow<List<Note>> {
        return noteLocalDataSource.searchNoteByTitleOrDesc(query).map { notesEntity ->
            notesEntity.toNotes()
        }
    }

    override suspend fun saveNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return noteLocalDataSource.saveNote(noteEntity)
    }

    override suspend fun updateNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return noteLocalDataSource.updateNote(noteEntity)
    }

    override suspend fun deleteNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return noteLocalDataSource.deleteNote(noteEntity)
    }

}