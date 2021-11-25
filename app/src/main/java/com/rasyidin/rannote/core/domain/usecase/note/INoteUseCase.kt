package com.rasyidin.rannote.core.domain.usecase.note

import androidx.paging.PagingData
import com.rasyidin.rannote.core.domain.ResultState
import com.rasyidin.rannote.core.domain.model.note.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface INoteUseCase {

    val listNotesOrderByDateDesc: StateFlow<ResultState<PagingData<Note>>>
    val listNotesOrderByDateAsc: StateFlow<ResultState<PagingData<Note>>>
    val listNotesOrderByTitleDesc: StateFlow<ResultState<PagingData<Note>>>
    val listNotesOrderByTitleAsc: StateFlow<ResultState<PagingData<Note>>>
    val searchNotes: SharedFlow<ResultState<PagingData<Note>>>

    suspend fun getAllNotesOrderByDateDesc(coroutineScope: CoroutineScope)

    suspend fun getAllNotesOrderByDateAsc(coroutineScope: CoroutineScope)

    suspend fun getAllNotesOrderByTitleDesc(coroutineScope: CoroutineScope)

    suspend fun getAllNotesOrderByTitleAsc(coroutineScope: CoroutineScope)

    suspend fun searchNoteByTitleOrDesc(query: String, coroutineScope: CoroutineScope)

    suspend fun saveNote(note: Note)

    suspend fun deleteNote(note: Note)
}