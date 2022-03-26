package com.dotescapesoftwarelab.rapidnotes.domain.repository

import com.dotescapesoftwarelab.rapidnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Note?

}