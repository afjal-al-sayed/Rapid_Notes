package com.dotescapesoftwarelab.rapidnotes.data.repository

import com.dotescapesoftwarelab.rapidnotes.data.local_source.NoteDao
import com.dotescapesoftwarelab.rapidnotes.domain.model.Note
import com.dotescapesoftwarelab.rapidnotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    val noteDAO: NoteDao
): NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDAO.getAllNotes()
    }

    override suspend fun insertNote(note: Note) {
        noteDAO.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDAO.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDAO.getNoteById(id)
    }

}