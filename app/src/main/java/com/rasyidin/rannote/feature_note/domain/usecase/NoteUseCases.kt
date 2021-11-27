package com.rasyidin.rannote.feature_note.domain.usecase

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val saveNote: SaveNote,
    val searchNotes: SearchNotes
)
