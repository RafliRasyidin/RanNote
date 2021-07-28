package com.rasyidin.rannote.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.rasyidin.rannote.core.data.source.local.NoteLocalDataSource
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.repository.INoteRepository
import com.rasyidin.rannote.core.utils.toNote
import com.rasyidin.rannote.core.utils.toNoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteLocalDataSource: NoteLocalDataSource,
) : INoteRepository {

    override fun getAllNotesOrderByDateDesc(): Flow<PagingData<Note>> {
        return noteLocalDataSource.getAllNotesOrderByDateDesc().flow.map { pagingData ->
            pagingData.map {
                it.toNote()
            }
        }
    }

    override fun getAllNotesOrderByDateAsc(): Flow<PagingData<Note>> {
        return noteLocalDataSource.getAllNotesOrderByDateAsc().flow.map { pagingData ->
            pagingData.map { it.toNote() }
        }
    }

    override fun getAllNotesOrderByTitleDesc(): Flow<PagingData<Note>> {
        return  noteLocalDataSource.getAllNotesOrderByTitleDesc().flow.map { pagingData ->
            pagingData.map { it.toNote() }
        }
    }

    override fun getAllNotesOrderByTitleAsc(): Flow<PagingData<Note>> {
        return noteLocalDataSource.getAllNotesOrderByTitleAsc().flow.map { pagingData ->
            pagingData.map { it.toNote() }
        }
    }

    override fun searchNoteByTitleOrDesc(query: String): Flow<PagingData<Note>> {
        return noteLocalDataSource.searchNoteByTitleOrDesc(query).flow.map { notesEntity ->
            notesEntity.map {
                it.toNote()
            }
        }
    }

    override suspend fun saveNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return noteLocalDataSource.saveNote(noteEntity)
    }

    override suspend fun deleteNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return noteLocalDataSource.deleteNote(noteEntity)
    }

}