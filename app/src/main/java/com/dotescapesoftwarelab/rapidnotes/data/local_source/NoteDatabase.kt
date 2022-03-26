package com.dotescapesoftwarelab.rapidnotes.data.local_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dotescapesoftwarelab.rapidnotes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = true
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }

}