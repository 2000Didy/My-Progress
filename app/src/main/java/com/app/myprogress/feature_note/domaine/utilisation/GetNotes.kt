package com.app.myprogress.feature_note.domaine.utilisation

import com.app.myprogress.feature_note.domaine.depot.NoteDepot
import com.app.myprogress.feature_note.domaine.modele.Note
import com.app.myprogress.feature_note.domaine.outils.OrdreNote
import com.app.myprogress.feature_note.domaine.outils.OrdreType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetNotes (
    private val depot:NoteDepot
        ){
    operator fun invoke(ordreNote: OrdreNote = OrdreNote.Date(OrdreType.Descending)): Flow<List<Note>> {
        return depot.getNotes().map { notes ->
            when (ordreNote.ordreType) {
                is OrdreType.Ascending -> {
                    when (ordreNote) {
                        is OrdreNote.Title -> notes.sortedBy { it.title.lowercase() }
                        is OrdreNote.Date -> notes.sortedBy { it.timestamp }
                        is OrdreNote.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrdreType.Descending -> {
                    when (ordreNote) {
                        is OrdreNote.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is OrdreNote.Date -> notes.sortedByDescending { it.timestamp }
                        is OrdreNote.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}
