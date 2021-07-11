package com.rasyidin.rannote.core.domain.usecase.note

import androidx.paging.PagingData
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow

class NoteInteractors(private val noteRepository: INoteRepository) : INoteUseCase {

    override fun getAllNotes(): Flow<PagingData<Note>> {
        return noteRepository.getAllNotes()
    }

    override fun searchNoteByTitleOrDesc(query: String): Flow<List<Note>> {
        return noteRepository.searchNoteByTitleOrDesc(query)
    }

    override suspend fun saveNote(note: Note) {
        return noteRepository.saveNote(note)
    }

    override suspend fun updateNote(note: Note) {
        return noteRepository.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteRepository.deleteNote(note)
    }
}