package com.dotescapesoftwarelab.rapidnotes.ui.screens.add_edit_note_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TransientTextField(
    text: String,
    hint: String,
    singleLine: Boolean = false,
    onValueChanged: (String) -> Unit,
//    onFocusChanged: (FocusState) -> Unit,
    textStyle: TextStyle = TextStyle(),
    isHintVisible: Boolean = true,
    focusManager: FocusManager
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        BasicTextField(
            value = text,
            onValueChange = { onValueChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                /*.onFocusChanged {
                    onFocusChanged(it)
                }*/,
            textStyle = textStyle.copy(
                color = Color.Black
            ),
            singleLine = singleLine,
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() },
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = if(singleLine) ImeAction.Next else ImeAction.Default
            )
        )
        if(isHintVisible){
            Text(
                text = hint,
                style = textStyle,
                color = Color.DarkGray
            )
        }
    }
}