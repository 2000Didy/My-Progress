package com.app.myprogress.feature_note.presentation.notes

import com.app.myprogress.feature_note.domaine.modele.Note
import com.app.myprogress.feature_note.domaine.outils.OrdreNote
import com.app.myprogress.feature_note.domaine.outils.OrdreType

data class NotesState (
    val notes: List<Note> = emptyList(),
    val ordreNote: OrdreNote = OrdreNote.Date(OrdreType.Descending),
    val isOrderSectionVisible: Boolean = false
)
