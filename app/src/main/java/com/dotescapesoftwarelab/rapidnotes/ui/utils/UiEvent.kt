package com.dotescapesoftwarelab.rapidnotes.ui.utils

import androidx.compose.ui.graphics.Color

sealed class UiEvent{
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvent()
    data class ShowToast(
        val message: String
    ): UiEvent()
    data class NavigateTo(val route: String): UiEvent()
    object NavigateUp: UiEvent()
}
