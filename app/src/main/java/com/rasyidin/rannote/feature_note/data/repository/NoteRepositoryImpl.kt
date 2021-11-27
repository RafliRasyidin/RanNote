package com.rasyidin.rannote.feature_note.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.rasyidin.rannote.feature_note.domain.util.toNote
import com.rasyidin.rannote.feature_note.domain.util.toNoteEntity
import com.rasyidin.rannote.feature_note.data.source.local.NoteLocalDataSource
import com.rasyidin.rannote.feature_note.domain.model.Note
import com.rasyidin.rannote.feature_note.domain.repository.NoteRepository
import com.rasyidin.rannote.feature_note.domain.util.SortUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: NoteLocalDataSource,
) : NoteRepository {


    override fun getNotes(sort: String): Flow<PagingData<Note>> {
        val query = SortUtils.getSortedQuery(sort)
        return localDataSource.getNotes(query).map { pagingData ->
            pagingData.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }

    override fun searchNotes(
        query: String
    ): Flow<PagingData<Note>> {
        return localDataSource.searchNoteByTitleOrDesc(query).map { pagingData ->
            pagingData.map { noteEntity ->
                noteEntity.toNote()
            }
        }
    }

    override suspend fun saveNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return localDataSource.saveNote(noteEntity)
    }

    override suspend fun deleteNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return localDataSource.deleteNote(noteEntity)
    }

}