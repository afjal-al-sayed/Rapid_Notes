package com.dotescapesoftwarelab.rapidnotes.data.local_source

import androidx.room.*
import com.dotescapesoftwarelab.rapidnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SElECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROm notes WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

}