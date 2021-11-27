package com.rasyidin.rannote.feature_note.domain.repository

import androidx.paging.PagingData
import com.rasyidin.rannote.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(sort: String): Flow<PagingData<Note>>

    fun searchNotes(
        query: String
    ): Flow<PagingData<Note>>

    suspend fun saveNote(note: Note)

    suspend fun deleteNote(note: Note)
}