package com.rasyidin.rannote.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.rasyidin.rannote.core.data.source.local.NoteLocalDataSource
import com.rasyidin.rannote.core.domain.ResultState
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.domain.repository.INoteRepository
import com.rasyidin.rannote.core.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteLocalDataSource: NoteLocalDataSource,
) : INoteRepository {

    private val _listNotesOrderByDateDesc: MutableStateFlow<ResultState<PagingData<Note>>> = idle()
    private val _listNotesOrderByDateAsc: MutableStateFlow<ResultState<PagingData<Note>>> = idle()
    private val _listNotesOrderByTitleDesc: MutableStateFlow<ResultState<PagingData<Note>>> = idle()
    private val _listNotesOrderByTitleAsc: MutableStateFlow<ResultState<PagingData<Note>>> = idle()
    private val _searchNotes: MutableSharedFlow<ResultState<PagingData<Note>>> = idle()

    override val listNotesOrderByDateDesc: StateFlow<ResultState<PagingData<Note>>>
        get() = _listNotesOrderByDateDesc

    override val listNotesOrderByDateAsc: StateFlow<ResultState<PagingData<Note>>>
        get() = _listNotesOrderByDateAsc

    override val listNotesOrderByTitleDesc: StateFlow<ResultState<PagingData<Note>>>
        get() = _listNotesOrderByTitleDesc

    override val listNotesOrderByTitleAsc: StateFlow<ResultState<PagingData<Note>>>
        get() = _listNotesOrderByTitleAsc

    override val searchNotes: SharedFlow<ResultState<PagingData<Note>>>
        get() = _searchNotes

    override suspend fun getAllNotesOrderByDateDesc(coroutineScope: CoroutineScope) {
        noteLocalDataSource.getAllNotesOrderByDateDesc(coroutineScope).collect { pagingData ->
            fetch {
                pagingData
            }.map { result ->
                mapResult(result) {
                    this.map { noteEntity ->
                        noteEntity.toNote()
                    }
                }
            }.collectLatest { result ->
                _listNotesOrderByDateDesc.value = result
            }
        }
    }

    override suspend fun getAllNotesOrderByDateAsc(coroutineScope: CoroutineScope) {
        noteLocalDataSource.getAllNotesOrderByDateAsc(coroutineScope).collect { pagingData ->
            fetch {
                pagingData
            }.map { result ->
                mapResult(result) {
                    this.map { noteEntity ->
                        noteEntity.toNote()
                    }
                }
            }.collectLatest { result ->
                _listNotesOrderByDateAsc.value = result
            }
        }
    }

    override suspend fun getAllNotesOrderByTitleDesc(coroutineScope: CoroutineScope) {
        noteLocalDataSource.getAllNotesOrderByTitleDesc(coroutineScope).collect { pagingData ->
            fetch {
                pagingData
            }.map { result ->
                mapResult(result) {
                    this.map { noteEntity ->
                        noteEntity.toNote()
                    }
                }
            }.collectLatest { result ->
                _listNotesOrderByTitleDesc.value = result
            }
        }
    }

    override suspend fun getAllNotesOrderByTitleAsc(coroutineScope: CoroutineScope) {
        noteLocalDataSource.getAllNotesOrderByTitleAsc(coroutineScope).collect { pagingData ->
            fetch {
                pagingData
            }.map { result ->
                mapResult(result) {
                    this.map { noteEntity ->
                        noteEntity.toNote()
                    }
                }
            }.collectLatest { result ->
                _listNotesOrderByTitleAsc.value = result
            }
        }
    }

    override suspend fun searchNoteByTitleOrDesc(
        query: String,
        coroutineScope: CoroutineScope
    ){
        noteLocalDataSource.searchNoteByTitleOrDesc(query, coroutineScope).collect { pagingData ->
            fetch {
                pagingData
            }.map { result ->
                mapResult(result) {
                    this.map { noteEntity ->
                        noteEntity.toNote()
                    }
                }
            }.collectLatest { result ->
                _searchNotes.emit(result)
            }
        }
    }

    override suspend fun saveNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return noteLocalDataSource.saveNote(noteEntity)
    }

    override suspend fun deleteNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        return noteLocalDataSource.deleteNote(noteEntity)
    }

}