package com.app.myprogress.feature_note.donnee.src_donnee

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.myprogress.feature_note.domaine.modele.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteBdd: RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}