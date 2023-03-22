package com.app.myprogress.feature_note.donnee.depot

import com.app.myprogress.feature_note.domaine.depot.NoteDepot
import com.app.myprogress.feature_note.domaine.modele.Note
import com.app.myprogress.feature_note.donnee.src_donnee.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteDepotImpl(
    private val dao: NoteDao
):NoteDepot {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}