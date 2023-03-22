package com.app.myprogress.feature_note.presentation.ajout_note_edit.composantes

import androidx.compose.ui.focus.FocusState

sealed class AjoutEditNoteEvent {
    data class EnteredTitle(val value: String) : AjoutEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AjoutEditNoteEvent()
    data class EnteredContent(val value: String) : AjoutEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AjoutEditNoteEvent()
    data class ChangeColor(val color: Int) : AjoutEditNoteEvent()
    object SaveNote : AjoutEditNoteEvent()
}