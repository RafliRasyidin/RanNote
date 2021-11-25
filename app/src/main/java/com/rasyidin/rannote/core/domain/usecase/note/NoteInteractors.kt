package com.rasyidin.rannote.core.domain.usecase.note

import androidx.paging.PagingData
import com.rasyidin.rannote.core.domain.ResultState
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.repository.INoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NoteInteractors @Inject constructor(private val noteRepository: INoteRepository) :
    INoteUseCase {

    override val listNotesOrderByDateDesc: StateFlow<ResultState<PagingData<Note>>>
        get() = noteRepository.listNotesOrderByDateDesc

    override val listNotesOrderByDateAsc: StateFlow<ResultState<PagingData<Note>>>
        get() = noteRepository.listNotesOrderByDateAsc

    override val listNotesOrderByTitleDesc: StateFlow<ResultState<PagingData<Note>>>
        get() = noteRepository.listNotesOrderByTitleDesc

    override val listNotesOrderByTitleAsc: StateFlow<ResultState<PagingData<Note>>>
        get() = noteRepository.listNotesOrderByTitleAsc

    override val searchNotes: SharedFlow<ResultState<PagingData<Note>>>
        get() = noteRepository.searchNotes

    override suspend fun getAllNotesOrderByDateDesc(coroutineScope: CoroutineScope) {
        return noteRepository.getAllNotesOrderByDateDesc(coroutineScope)
    }

    override suspend fun getAllNotesOrderByDateAsc(coroutineScope: CoroutineScope) {
        return noteRepository.getAllNotesOrderByDateAsc(coroutineScope)
    }

    override suspend fun getAllNotesOrderByTitleDesc(coroutineScope: CoroutineScope) {
        return noteRepository.getAllNotesOrderByTitleDesc(coroutineScope)
    }

    override suspend fun getAllNotesOrderByTitleAsc(coroutineScope: CoroutineScope) {
        return noteRepository.getAllNotesOrderByTitleAsc(coroutineScope)
    }

    override suspend fun searchNoteByTitleOrDesc(
        query: String,
        coroutineScope: CoroutineScope
    ) {
        return noteRepository.searchNoteByTitleOrDesc(query, coroutineScope)
    }

    override suspend fun saveNote(note: Note) {
        return noteRepository.saveNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteRepository.deleteNote(note)
    }
}