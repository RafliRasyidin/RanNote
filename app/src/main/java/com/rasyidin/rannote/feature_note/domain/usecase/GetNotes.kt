package com.rasyidin.rannote.feature_note.domain.usecase

import androidx.paging.PagingData
import com.rasyidin.rannote.feature_note.domain.model.Note
import com.rasyidin.rannote.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(private val repository: NoteRepository) {

    operator fun invoke(query: String): Flow<PagingData<Note>> {
        return repository.getNotes(query)
    }
}