package com.app.myprogress.feature_note.domaine.outils

sealed class OrdreNote(val ordreType: OrdreType){
    class Title(ordreType: OrdreType) : OrdreNote(ordreType)
    class Date(ordreType: OrdreType) : OrdreNote(ordreType)
    class Color(ordreType: OrdreType) : OrdreNote(ordreType)

    fun copy( ordreType: OrdreType): OrdreNote {
        return when(this) {
            is Title -> Title(ordreType)
            is Date -> Date(ordreType)
            is Color -> Color(ordreType)
        }
    }
}
