package com.dotescapesoftwarelab.di

import android.app.Application
import androidx.room.Room
import com.dotescapesoftwarelab.rapidnotes.data.local_source.NoteDao
import com.dotescapesoftwarelab.rapidnotes.data.local_source.NoteDatabase
import com.dotescapesoftwarelab.rapidnotes.data.repository.NoteRepositoryImpl
import com.dotescapesoftwarelab.rapidnotes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDatabase{
        return Room.databaseBuilder(
            application.applicationContext,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(database: NoteDatabase) = database.noteDao

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository{
        return NoteRepositoryImpl(noteDao)
    }

}