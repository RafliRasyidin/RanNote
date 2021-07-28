package com.rasyidin.rannote.ui.feature.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.usecase.note.INoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCase: INoteUseCase) : ViewModel() {

    fun getAllNotes() = noteUseCase.getAllNotes().asLiveData()

    fun searchNotes(query: String) = noteUseCase.searchNoteByTitleOrDesc(query).asLiveData()

    fun saveNote(note: Note) = viewModelScope.launch {
        noteUseCase.saveNote(note)
    }
}