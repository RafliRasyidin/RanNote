package com.rasyidin.rannote.ui.feature.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.usecase.note.INoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCase: INoteUseCase) : ViewModel() {

    fun getAllNotesOrderByDateDesc() = noteUseCase.getAllNotesOrderByDateDesc().asLiveData()

    fun getAllNotesOrderByDateAsc() = noteUseCase.getAllNotesOrderByDateAsc().asLiveData()

    fun getAllNotesOrderByTitleDesc() = noteUseCase.getAllNotesOrderByTitleDesc().asLiveData()

    fun getAllNotesOrderByTitleAsc() = noteUseCase.getAllNotesOrderByTitleAsc().asLiveData()

    fun searchNotes(query: String) = noteUseCase.searchNoteByTitleOrDesc(query).asLiveData()

    fun saveNote(note: Note) = viewModelScope.launch {
        noteUseCase.saveNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteUseCase.deleteNote(note)
    }
}