package com.rasyidin.rannote.core.data.source.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rasyidin.rannote.core.data.source.local.entity.note.NoteEntity
import com.rasyidin.rannote.core.data.source.local.room.NoteDao
import okio.IOException

/*
class NotePagingSource(private val noteDao: NoteDao) : PagingSource<Int, NoteEntity>() {

    private companion object {
        const val INITIAL_PAGE = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NoteEntity> {
        return try {
            val pageNumber = params.key ?: INITIAL_PAGE
            val notes = noteDao.getAllNotes()
            return LoadResult.Page(
                data = notes,
                prevKey = if (pageNumber == INITIAL_PAGE) null else pageNumber - 1,
                nextKey = if (notes.isNullOrEmpty()) null else pageNumber + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        } catch (e : IOException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NoteEntity>): Int? = null
}*/
