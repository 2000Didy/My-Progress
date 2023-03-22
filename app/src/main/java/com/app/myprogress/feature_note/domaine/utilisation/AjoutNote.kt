package com.app.myprogress.feature_note.domaine.utilisation

import com.app.myprogress.feature_note.domaine.depot.NoteDepot
import com.app.myprogress.feature_note.domaine.modele.InvalidNoteException
import com.app.myprogress.feature_note.domaine.modele.Note

class AjoutNote (
    private val depot : NoteDepot
){
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Le titre ne peut pas être vide.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Le contenu de la note ne doit pas être vide.")
        }
        depot.insertNote(note)
    }
}