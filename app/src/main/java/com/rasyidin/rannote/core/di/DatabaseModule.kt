package com.rasyidin.rannote.core.di

import android.content.Context
import androidx.room.Room
import com.rasyidin.rannote.feature_note.data.source.local.RanNoteDatabase
import com.rasyidin.rannote.core.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesNoteDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            RanNoteDatabase::class.java,
            DATABASE_NAME
        ).build()

}