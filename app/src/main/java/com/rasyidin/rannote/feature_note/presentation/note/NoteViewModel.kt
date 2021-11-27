package com.rasyidin.rannote.feature_note.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.rasyidin.rannote.feature_note.domain.model.InvalidNoteException
import com.rasyidin.rannote.feature_note.domain.model.Note
import com.rasyidin.rannote.feature_note.domain.usecase.NoteUseCases
import com.rasyidin.rannote.feature_note.domain.util.SortUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCase: NoteUseCases) : ViewModel() {

    private val _notes = MutableStateFlow<PagingData<Note>>(PagingData.empty())
    val notes: StateFlow<PagingData<Note>> = _notes

    private var getNotesJob: Job? = null

    init {
        getNotes(SortUtils.DATE_DESC)
    }

    fun searchNotes(query: String) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCase.searchNotes(query).onEach { pagingData ->
            _notes.value = pagingData
        }.launchIn(viewModelScope)
    }

    fun getNotes(sort: String) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCase.getNotes(sort).onEach { pagingData ->
            _notes.value = pagingData
        }.launchIn(viewModelScope)
    }
}