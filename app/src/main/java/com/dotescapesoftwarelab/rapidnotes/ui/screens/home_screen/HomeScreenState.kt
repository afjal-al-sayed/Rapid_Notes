package com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen

import com.dotescapesoftwarelab.rapidnotes.domain.model.Note

data class HomeScreenState(
    val notes: List<Note> = emptyList(),
)
