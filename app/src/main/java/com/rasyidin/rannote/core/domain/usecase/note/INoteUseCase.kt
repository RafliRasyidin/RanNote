package com.rasyidin.rannote.core.domain.usecase.note

import androidx.paging.PagingData
import com.rasyidin.rannote.core.domain.model.note.Note
import kotlinx.coroutines.flow.Flow

interface INoteUseCase {

    fun getAllNotesOrderByDateDesc(): Flow<PagingData<Note>>

    fun getAllNotesOrderByDateAsc(): Flow<PagingData<Note>>

    fun getAllNotesOrderByTitleDesc(): Flow<PagingData<Note>>

    fun getAllNotesOrderByTitleAsc(): Flow<PagingData<Note>>

    fun searchNoteByTitleOrDesc(query: String): Flow<PagingData<Note>>

    suspend fun saveNote(note: Note)

    suspend fun deleteNote(note: Note)
}