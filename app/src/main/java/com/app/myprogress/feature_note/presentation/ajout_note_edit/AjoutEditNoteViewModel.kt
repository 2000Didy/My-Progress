package com.app.myprogress.feature_note.presentation.ajout_note_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.myprogress.feature_note.domaine.modele.InvalidNoteException
import com.app.myprogress.feature_note.domaine.modele.Note
import com.app.myprogress.feature_note.domaine.utilisation.NoteUtilisation
import com.app.myprogress.feature_note.presentation.ajout_note_edit.composantes.AjoutEditNoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject






@HiltViewModel
class AjoutEditNoteViewModel @Inject constructor(
    private val noteUtilisation: NoteUtilisation,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(
        EtatNoteTextField(
            hint = ""
        )
    )
    val noteTitle: State<EtatNoteTextField> = _noteTitle

    private val _noteContent = mutableStateOf(
        EtatNoteTextField(
            hint = ""
        )
    )
    val noteContent: State<EtatNoteTextField> = _noteContent

    private val _noteColor = mutableStateOf<Int>(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFLow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let {  noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUtilisation.getNote(noteId)?.also {  note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AjoutEditNoteEvent) {
        when (event) {
            is AjoutEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AjoutEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AjoutEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AjoutEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }
            is AjoutEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AjoutEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUtilisation.ajoutNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Ne peut pas être enregistrer"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()

    }

}
