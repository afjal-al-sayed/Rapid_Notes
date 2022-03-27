package com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotescapesoftwarelab.rapidnotes.domain.model.Note
import com.dotescapesoftwarelab.rapidnotes.domain.repository.NoteRepository
import com.dotescapesoftwarelab.rapidnotes.ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val noteRepository: NoteRepository
): ViewModel() {

    private val _homeScreenState = mutableStateOf(HomeScreenState())
    val homeScreenState: State<HomeScreenState> = _homeScreenState
    private var deletedNote: Note? = null
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getAllNotes()
    }

    fun getAllNotes(){
        viewModelScope.launch {
            noteRepository.getAllNotes().collect { newList ->
                _homeScreenState.value = HomeScreenState(
                    notes = newList
                )
            }
        }
    }

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnNoteDeleteEvent -> {
                deletedNote = event.note
                viewModelScope.launch {
                    noteRepository.deleteNote(event.note)
                }
                showSnackBar(message = "Note deleted", action = "Undo")
            }
            is HomeScreenEvent.OnUndoNoteEvent -> {
                deletedNote?.let { note ->
                    viewModelScope.launch {
                        noteRepository.insertNote(note)
                    }
                }
            }
        }
    }

    fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun showSnackBar(message: String, action: String?){
        sendUiEvent(UiEvent.ShowSnackBar(message, action))
    }

}