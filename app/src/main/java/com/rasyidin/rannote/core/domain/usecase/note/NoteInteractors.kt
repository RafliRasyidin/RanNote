package com.rasyidin.rannote.core.domain.usecase.note

import androidx.paging.PagingData
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteInteractors @Inject constructor(private val noteRepository: INoteRepository) : INoteUseCase {

    override fun getAllNotesOrderByDateDesc(): Flow<PagingData<Note>> {
        return noteRepository.getAllNotesOrderByDateDesc()
    }

    override fun searchNoteByTitleOrDesc(query: String): Flow<PagingData<Note>> {
        return noteRepository.searchNoteByTitleOrDesc(query)
    }

    override suspend fun saveNote(note: Note) {
        return noteRepository.saveNote(note)
    }

    override fun getAllNotesOrderByDateAsc(): Flow<PagingData<Note>> {
        return noteRepository.getAllNotesOrderByDateAsc()
    }

    override fun getAllNotesOrderByTitleDesc(): Flow<PagingData<Note>> {
        return noteRepository.getAllNotesOrderByTitleDesc()
    }

    override fun getAllNotesOrderByTitleAsc(): Flow<PagingData<Note>> {
        return noteRepository.getAllNotesOrderByTitleAsc()
    }

    override suspend fun deleteNote(note: Note) {
        return noteRepository.deleteNote(note)
    }
}