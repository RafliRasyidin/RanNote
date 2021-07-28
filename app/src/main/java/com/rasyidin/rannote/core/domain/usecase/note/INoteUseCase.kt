package com.rasyidin.rannote.core.domain.usecase.note

import androidx.paging.PagingData
import com.rasyidin.rannote.core.domain.model.note.Note
import kotlinx.coroutines.flow.Flow

interface INoteUseCase {

    fun getAllNotes(): Flow<PagingData<Note>>

    fun searchNoteByTitleOrDesc(query: String): Flow<PagingData<Note>>

    suspend fun saveNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}