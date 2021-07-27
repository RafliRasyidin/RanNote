package com.rasyidin.rannote.ui.feature.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rasyidin.rannote.core.domain.usecase.note.INoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteVIewModel @Inject constructor(private val noteUseCase: INoteUseCase) : ViewModel() {

    val getAllNotes = noteUseCase.getAllNotes().asLiveData()

    fun searchNotes(query: String) = noteUseCase.searchNoteByTitleOrDesc(query).asLiveData()
}