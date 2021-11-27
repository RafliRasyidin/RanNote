package com.rasyidin.rannote.core.di

import com.rasyidin.rannote.feature_note.domain.repository.NoteRepository
import com.rasyidin.rannote.feature_note.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesNoteUseCase(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            saveNote = SaveNote(repository),
            searchNotes = SearchNotes(repository)
        )
    }
}