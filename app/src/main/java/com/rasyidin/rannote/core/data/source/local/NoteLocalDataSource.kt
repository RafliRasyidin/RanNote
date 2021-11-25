package com.rasyidin.rannote.core.data.source.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity
import com.rasyidin.rannote.core.data.source.local.room.dao.NoteDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class NoteLocalDataSource @Inject constructor(
    private val noteDao: NoteDao
) {

    private val pagingConfig = PagingConfig(10)

    fun getAllNotesOrderByDateDesc(coroutineScope: CoroutineScope) = Pager(
        config = pagingConfig
    ) {
        noteDao.getAllNotesOrderByDateDesc()
    }.flow.cachedIn(coroutineScope)

    fun getAllNotesOrderByDateAsc(coroutineScope: CoroutineScope) = Pager(
        config = pagingConfig
    ) {
        noteDao.getAllNotesOrderByDateAsc()
    }.flow.cachedIn(coroutineScope)

    fun getAllNotesOrderByTitleAsc(coroutineScope: CoroutineScope) = Pager(
        config = pagingConfig
    ) {
        noteDao.getAllNotesOrderByTitleAsc()
    }.flow.cachedIn(coroutineScope)

    fun getAllNotesOrderByTitleDesc(coroutineScope: CoroutineScope) = Pager(
        config = pagingConfig
    ) {
        noteDao.getAllNotesOrderByTitleDesc()
    }.flow.cachedIn(coroutineScope)

    fun searchNoteByTitleOrDesc(query: String, coroutineScope: CoroutineScope) = Pager(
        config = pagingConfig
    ) {
        noteDao.searchNoteByTitleOrDesc(query)
    }.flow.cachedIn(coroutineScope)

    suspend fun saveNote(note: NoteEntity) = noteDao.saveNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

}