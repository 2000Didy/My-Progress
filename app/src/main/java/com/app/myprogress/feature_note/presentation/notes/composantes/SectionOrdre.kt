package com.app.myprogress.feature_note.presentation.notes.composantes

import android.provider.MediaStore.Audio.Radio
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.myprogress.feature_note.domaine.outils.OrdreNote
import com.app.myprogress.feature_note.domaine.outils.OrdreType

@Composable
fun SectionOrdre(
    modifier: Modifier = Modifier,
    ordreNote:OrdreNote= OrdreNote.Date(OrdreType.Descending),
    onOrderChange: (OrdreNote) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            RadioButtonDef(
                text = "Titre",
                selected = ordreNote is OrdreNote.Title,
                onSelect = { onOrderChange(OrdreNote.Title(ordreNote.ordreType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            RadioButtonDef(
                text = "Date",
                selected = ordreNote is OrdreNote.Date,
                onSelect = { onOrderChange(OrdreNote.Date(ordreNote.ordreType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            RadioButtonDef(
                text = "Couleur",
                selected = ordreNote is OrdreNote.Color,
                onSelect = { onOrderChange(OrdreNote.Color(ordreNote.ordreType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {

            RadioButtonDef(
                text = "Croissant",
                selected = ordreNote.ordreType is OrdreType.Ascending,
                onSelect = {
                    onOrderChange(ordreNote.copy(OrdreType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            RadioButtonDef(
                text = "Decroissant",
                selected = ordreNote.ordreType is OrdreType.Descending,
                onSelect = {
                    onOrderChange(ordreNote.copy(OrdreType.Descending))
                }
            )

        }

    }
}