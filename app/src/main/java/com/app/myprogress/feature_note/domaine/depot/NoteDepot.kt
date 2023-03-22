package com.app.myprogress.feature_note.domaine.depot

import com.app.myprogress.feature_note.domaine.modele.Note
import kotlinx.coroutines.flow.Flow

interface NoteDepot {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}