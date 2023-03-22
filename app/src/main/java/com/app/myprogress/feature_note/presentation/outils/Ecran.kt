package com.app.myprogress.feature_note.presentation.outils

sealed class Ecran(val route: String) {
    object EcranNotes: Ecran("ecran_notes")
    object AjoutEcranEditNote: Ecran("ajout_ecran_edit_note")
}
