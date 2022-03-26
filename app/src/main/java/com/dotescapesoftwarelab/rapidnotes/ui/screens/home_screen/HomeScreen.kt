package com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val state = viewModel.homeScreenState
    val notes = state.value.notes

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            items(notes){ note ->
                Column{
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
                }
            }
        }
    }
}