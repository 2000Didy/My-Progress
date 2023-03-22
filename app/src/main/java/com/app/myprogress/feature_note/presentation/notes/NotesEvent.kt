package com.app.myprogress.feature_note.presentation.notes

import com.app.myprogress.feature_note.domaine.modele.Note
import com.app.myprogress.feature_note.domaine.outils.OrdreNote

sealed class NotesEvent {
    data class Ordre(val ordreNote: OrdreNote) : NotesEvent()
    data class SuppNote(val note: Note): NotesEvent()
    object RestaurerNote: NotesEvent()
    object  ToggleOrderSection: NotesEvent()
}
