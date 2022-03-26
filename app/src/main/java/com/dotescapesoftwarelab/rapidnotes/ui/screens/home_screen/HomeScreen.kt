package com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen.components.NoteItem

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val state = viewModel.homeScreenState
    val notes = state.value.notes

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Default.Add, "Add new note")
            }
        }
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
                NoteItem(note = note)
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