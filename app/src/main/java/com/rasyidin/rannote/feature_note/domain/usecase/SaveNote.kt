package com.rasyidin.rannote.feature_note.domain.usecase

import com.rasyidin.rannote.feature_note.domain.model.Note
import com.rasyidin.rannote.feature_note.domain.repository.NoteRepository

class SaveNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note) {
        repository.saveNote(note)
    }
}