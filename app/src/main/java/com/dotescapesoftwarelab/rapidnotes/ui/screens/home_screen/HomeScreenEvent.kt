package com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen

import com.dotescapesoftwarelab.rapidnotes.domain.model.Note

sealed class HomeScreenEvent{
    data class OnNoteDeleteEvent(val note: Note): HomeScreenEvent()
    object OnUndoNoteEvent: HomeScreenEvent()
}