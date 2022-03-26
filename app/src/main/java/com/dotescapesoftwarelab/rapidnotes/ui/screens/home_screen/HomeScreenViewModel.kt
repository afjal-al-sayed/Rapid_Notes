package com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotescapesoftwarelab.rapidnotes.domain.model.Note
import com.dotescapesoftwarelab.rapidnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val noteRepository: NoteRepository
): ViewModel() {

    private val _homeScreenState = mutableStateOf(HomeScreenState())
    val homeScreenState: State<HomeScreenState> = _homeScreenState

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

}