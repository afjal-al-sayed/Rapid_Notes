package com.dotescapesoftwarelab.rapidnotes.ui.utils

sealed class Screen(val route: String){
    object Home: Screen("home_screen")
    object AddEditNote: Screen("add_edit_note_screen")
}
