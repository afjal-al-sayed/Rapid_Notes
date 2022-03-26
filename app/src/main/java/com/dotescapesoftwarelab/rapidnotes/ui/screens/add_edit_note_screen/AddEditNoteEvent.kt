package com.dotescapesoftwarelab.rapidnotes.ui.screens.add_edit_note_screen

import androidx.compose.ui.graphics.Color

sealed class AddEditNoteEvent{
    data class OnTitleChanged(val title: String): AddEditNoteEvent()
    data class OnContentChanged(val content: String): AddEditNoteEvent()
    data class OnBackgroundColorChanged(val color: Color): AddEditNoteEvent()
    object OnSaveButtonPressed: AddEditNoteEvent()
}
