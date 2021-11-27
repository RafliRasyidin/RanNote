package com.rasyidin.rannote.core.di

import com.rasyidin.rannote.feature_note.data.source.local.RanNoteDatabase
import com.rasyidin.rannote.feature_todo.data.source.local.ToDoDao
import com.rasyidin.rannote.feature_todo.data.source.local.TodoLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoRepositoryModule {

    @Provides
    @Singleton
    fun providesToDoDao(db: RanNoteDatabase) = db.getTodoDao()

    @Provides
    @Singleton
    fun providesTodoLocalDataSource(toDoDao: ToDoDao): TodoLocalDataSource =
        TodoLocalDataSource(toDoDao)
}