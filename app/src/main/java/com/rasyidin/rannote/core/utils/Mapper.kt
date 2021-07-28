package com.rasyidin.rannote.core.utils

import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity
import com.rasyidin.rannote.core.domain.model.note.Note

fun NoteEntity.toNote() = Note(
    id = this.id,
    title = this.title,
    desc = this.desc,
    colorCard = this.colorCard,
    colorTitle = this.colorTitle,
    colorDesc = this.colorDesc,
    date = this.date
)

fun Note.toNoteEntity() = NoteEntity(
    id = this.id,
    title = this.title,
    desc = this.desc,
    colorCard = this.colorCard,
    colorTitle = this.colorTitle,
    colorDesc = this.colorDesc,
    date = this.date
)

fun List<NoteEntity>.toNotes(): List<Note> {
    if (this.isNullOrEmpty()) return emptyList()
    val notes = ArrayList<Note>()
    this.map {
        val note = Note(
            id = it.id,
            title = it.title,
            desc = it.desc,
            colorCard = it.colorCard,
            colorTitle = it.colorTitle,
            colorDesc = it.colorDesc,
            date = it.date
        )
        notes.add(note)
    }
    return notes
}



