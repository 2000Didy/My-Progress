package com.app.myprogress.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.myprogress.feature_note.domaine.modele.Note
import com.app.myprogress.feature_note.domaine.outils.OrdreNote
import com.app.myprogress.feature_note.domaine.outils.OrdreType
import com.app.myprogress.feature_note.domaine.utilisation.NoteUtilisation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
   private val noteUtilisation: NoteUtilisation
):ViewModel(){

    private val _state= mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var noteRecementSupp: Note?=null
    private var getNotesJob: Job? = null
    init {
        getNotes(OrdreNote.Date(OrdreType.Descending))
    }
fun onEvent(event: NotesEvent){
    when (event){
        is NotesEvent.Ordre->{
                if (state.value.ordreNote::class == event.ordreNote::class &&
                    state.value.ordreNote.ordreType == event.ordreNote.ordreType
                ) {
                    return
                }
                getNotes(event.ordreNote)
        }
        is NotesEvent.SuppNote->{
            viewModelScope.launch{
                noteUtilisation.suppNote(event.note)
                noteRecementSupp=event.note
            }
        }
        is NotesEvent.RestaurerNote->{
            viewModelScope.launch{
                noteUtilisation.ajoutNote(noteRecementSupp?:return@launch)
                noteRecementSupp=null
            }
        }
        is NotesEvent.ToggleOrderSection->{
            _state.value = state.value.copy(
                isOrderSectionVisible = !state.value.isOrderSectionVisible)
        }
    }
}
    private fun getNotes(ordreNote: OrdreNote) {
        getNotesJob?.cancel()
       getNotesJob = noteUtilisation.getNotes(ordreNote)
           .onEach { notes ->
               _state.value = state.value.copy(
                   notes=notes,
                   ordreNote=ordreNote
               )
           }
           .launchIn(viewModelScope)
    }
}




