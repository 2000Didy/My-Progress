package com.app.myprogress.feature_note.domaine.outils

sealed class OrdreType{
    object Ascending: OrdreType()
    object Descending: OrdreType()
}
