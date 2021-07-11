package com.rasyidin.rannote.core.di

import com.rasyidin.rannote.core.data.repository.NoteRepository
import com.rasyidin.rannote.core.data.source.local.NoteLocalDataSource
import com.rasyidin.rannote.core.data.source.local.room.NoteDao
import com.rasyidin.rannote.core.data.source.local.room.RanNoteDatabase
import com.rasyidin.rannote.core.domain.repository.INoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteRepositoryModule {

    @Provides
    @Singleton
    fun providesNoteDao(db: RanNoteDatabase) = db.getNoteDao()

    @Provides
    @Singleton
    fun providesNoteLocalDataSource(noteDao: NoteDao) = NoteLocalDataSource(noteDao)

    @Provides
    @Singleton
    fun providesNoteRepository(noteLocalDataSource: NoteLocalDataSource): INoteRepository =
        NoteRepository(noteLocalDataSource)

}