package com.app.myprogress.feature_note.presentation.ajout_note_edit.composantes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle

@Composable
fun HintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle.copy(
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Red,
                unfocusedBorderColor = Black),
            modifier = Modifier.fillMaxWidth(),
            label={Text("Titre")}
        )
        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = Color.Black)
        }
    }
}