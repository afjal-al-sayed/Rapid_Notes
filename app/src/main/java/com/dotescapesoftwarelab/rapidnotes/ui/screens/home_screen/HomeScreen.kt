package com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen.components.NoteItem
import com.dotescapesoftwarelab.rapidnotes.ui.utils.Screen
import com.dotescapesoftwarelab.rapidnotes.ui.utils.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val state = viewModel.homeScreenState
    val notes = state.value.notes
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if(result == SnackbarResult.ActionPerformed){
                        viewModel.onEvent(HomeScreenEvent.OnUndoNoteEvent)
                    }
                }
                is UiEvent.NavigateTo -> {
                    // TODO: 26-Mar-22
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate(Screen.AddEditNote.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Default.Add, "Add new note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            item {
                Text(
                    text = "Rapid Notes",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            items(notes){ note ->
                NoteItem(
                    note = note,
                    onDeleteClick = {
                        viewModel.onEvent(HomeScreenEvent.OnNoteDeleteEvent(note))
                    }
                )
                /*Column{
                    Text(
                        text = note.title
                    )
                    Text(
                        text = note.content
                    )
                    Text(
                        text = note.color.toString()
                    )
                    Text(
                        text = note.id.toString()
                    )
                    Text(
                        text = note.timestamp.toString()
                    )
                    Spacer(Modifier.height(8.dp))
                }*/
            }
        }
    }
}