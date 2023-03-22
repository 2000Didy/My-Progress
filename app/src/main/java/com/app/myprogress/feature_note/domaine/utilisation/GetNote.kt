package com.app.myprogress.feature_note.domaine.utilisation

import com.app.myprogress.feature_note.domaine.depot.NoteDepot
import com.app.myprogress.feature_note.domaine.modele.Note

class GetNote (
    private val depot: NoteDepot
    ) {
        suspend operator fun invoke(id: Int): Note? {
            return depot.getNoteById(id)
        }
}
