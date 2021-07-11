package com.rasyidin.rannote.di

import com.rasyidin.rannote.core.domain.repository.INoteRepository
import com.rasyidin.rannote.core.domain.usecase.note.INoteUseCase
import com.rasyidin.rannote.core.domain.usecase.note.NoteInteractors
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
    fun providesNoteUseCase(noteRepository: INoteRepository): INoteUseCase = NoteInteractors(noteRepository)
}