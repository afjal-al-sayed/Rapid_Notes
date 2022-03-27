package com.dotescapesoftwarelab.rapidnotes.ui.screens.add_edit_note_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotescapesoftwarelab.rapidnotes.domain.model.Note
import com.dotescapesoftwarelab.rapidnotes.domain.repository.NoteRepository
import com.dotescapesoftwarelab.rapidnotes.ui.theme.LightGreen
import com.dotescapesoftwarelab.rapidnotes.ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    val noteRepository: NoteRepository
): ViewModel() {

    var title by mutableStateOf("")
        private set
    var content by mutableStateOf("")
        private set
    var titleHintState by mutableStateOf(true)
        private set
    var contentHintState by mutableStateOf(true)
        private set
    var backgroundColor by mutableStateOf(LightGreen)
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var noteId: Int? = null


    fun onEvent(event: AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.OnTitleChanged -> {
                title = event.title
                updateHintState()
            }
            is AddEditNoteEvent.OnContentChanged -> {
                content = event.content
                updateHintState()
            }
            is AddEditNoteEvent.OnBackgroundColorChanged -> {
                backgroundColor = event.color
            }
            is AddEditNoteEvent.OnSaveButtonPressed -> {
                saveNote()
            }
        }
    }

    fun saveNote(){
        viewModelScope.launch {
            if (title.isBlank() && content.isBlank()) {
                showSnackBar("Both title & note can't be empty.")
                return@launch
            }
            val note = Note(
                title = title,
                content = content,
                color = backgroundColor.toArgb(),
                timestamp = System.currentTimeMillis(),
                id = noteId
            )
            noteRepository.insertNote(note)
            sendUiEvent(UiEvent.ShowToast(message = "Note saved successfully."))
//            delay(500L)
            sendUiEvent(UiEvent.NavigateUp)
        }
    }

    fun updateHintState(){
        titleHintState = title.isEmpty()
        contentHintState = content.isEmpty()
    }

    fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun showSnackBar(message: String){
        sendUiEvent(UiEvent.ShowSnackBar(message))
    }

}