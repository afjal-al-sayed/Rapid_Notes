package com.dotescapesoftwarelab.rapidnotes.ui.utils

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
