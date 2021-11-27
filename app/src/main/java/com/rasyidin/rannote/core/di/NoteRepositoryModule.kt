package com.rasyidin.rannote.core.di

import com.rasyidin.rannote.feature_note.data.repository.NoteRepositoryImpl
import com.rasyidin.rannote.feature_note.data.source.local.NoteLocalDataSource
import com.rasyidin.rannote.feature_note.data.source.local.RanNoteDatabase
import com.rasyidin.rannote.feature_note.data.source.local.NoteDao
import com.rasyidin.rannote.feature_note.domain.repository.NoteRepository
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
    fun providesNoteLocalDataSource(noteDao: NoteDao) =
        NoteLocalDataSource(noteDao)

    @Provides
    @Singleton
    fun providesNoteRepository(noteLocalDataSource: NoteLocalDataSource): NoteRepository =
        NoteRepositoryImpl(noteLocalDataSource)

}