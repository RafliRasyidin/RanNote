package com.rasyidin.rannote.core.domain.repository

import androidx.paging.PagingData
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity
import com.rasyidin.rannote.core.domain.model.note.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    fun getAllNotes(): Flow<PagingData<Note>>

    fun searchNoteByTitleOrDesc(query: String): Flow<PagingData<Note>>

    suspend fun saveNote(note: Note)

    suspend fun deleteNote(note: Note)
}