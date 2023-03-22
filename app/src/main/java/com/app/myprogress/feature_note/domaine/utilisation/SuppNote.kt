package com.app.myprogress.feature_note.domaine.utilisation

import com.app.myprogress.feature_note.domaine.depot.NoteDepot
import com.app.myprogress.feature_note.domaine.modele.Note


class SuppNote (
    private val depot: NoteDepot
    ) {
        suspend operator fun invoke(note: Note){
    depot.deleteNote(note)}
}