package com.rasyidin.rannote.ui.feature.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.usecase.note.INoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class
NoteViewModel @Inject constructor(private val noteUseCase: INoteUseCase) : ViewModel() {

    val listNotesOrderByDateDesc =
        noteUseCase.listNotesOrderByDateDesc

    val listNotesOrderByDateAsc =
        noteUseCase.listNotesOrderByDateAsc

    val listNotesOrderByTitleDesc =
        noteUseCase.listNotesOrderByTitleDesc

    val listNotesOrderByTitleAsc =
        noteUseCase.listNotesOrderByTitleAsc

    val searchNotes = noteUseCase.searchNotes

    fun getAllNotesOrderByDateDesc() = viewModelScope.launch(Dispatchers.IO) {
        noteUseCase.getAllNotesOrderByDateDesc(viewModelScope)
    }

    fun getAllNotesOrderByDateAsc() = viewModelScope.launch(Dispatchers.IO) {
        noteUseCase.getAllNotesOrderByDateAsc(viewModelScope)
    }

    fun getAllNotesOrderByTitleDesc() = viewModelScope.launch(Dispatchers.IO) {
        noteUseCase.getAllNotesOrderByTitleDesc(viewModelScope)
    }

    fun getAllNotesOrderByTitleAsc() = viewModelScope.launch(Dispatchers.IO) {
        noteUseCase.getAllNotesOrderByTitleAsc(viewModelScope)
    }

    fun searchNotes(query: String) = viewModelScope.launch {
        noteUseCase.searchNoteByTitleOrDesc(query, viewModelScope)
    }

    fun saveNote(note: Note) = viewModelScope.launch {
        noteUseCase.saveNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteUseCase.deleteNote(note)
    }
}