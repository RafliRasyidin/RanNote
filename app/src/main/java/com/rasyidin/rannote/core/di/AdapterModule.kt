package com.rasyidin.rannote.core.di

import com.rasyidin.rannote.ui.adapter.note.NoteAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Provides
    @Singleton
    fun providesNoteAdapter() = NoteAdapter()
}