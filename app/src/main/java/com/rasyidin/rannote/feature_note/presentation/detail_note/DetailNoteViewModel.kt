package com.rasyidin.rannote.feature_note.presentation.detail_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.rannote.feature_note.domain.model.InvalidNoteException
import com.rasyidin.rannote.feature_note.domain.model.Note
import com.rasyidin.rannote.feature_note.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNoteViewModel @Inject constructor(private val useCase: NoteUseCases): ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow

    fun saveNote(note: Note) = viewModelScope.launch {
        try {
            useCase.saveNote(note)
            _eventFlow.emit(UiEvent.SaveNote)
        } catch (e: InvalidNoteException) {
            _eventFlow.emit(UiEvent.GetException(e.message ?: "Couldn't save note"))
        } catch (e: Exception) {
            _eventFlow.emit(UiEvent.GetException(e.message ?: "Something Wrong"))
        }
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        try {
            useCase.deleteNote(note)
            _eventFlow.emit(UiEvent.DeleteNote)
        } catch (e: InvalidNoteException) {
            _eventFlow.emit(UiEvent.GetException(e.message ?: "Couldn't delete note"))
        } catch (e: Exception) {
            _eventFlow.emit(UiEvent.GetException(e.message ?: "Something Wrong"))
        }
    }

    sealed class UiEvent {
        data class GetException(val messageError: String): UiEvent()
        object SaveNote: UiEvent()
        object DeleteNote: UiEvent()
    }
}