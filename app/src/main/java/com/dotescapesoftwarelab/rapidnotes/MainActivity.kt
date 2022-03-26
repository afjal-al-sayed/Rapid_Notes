package com.dotescapesoftwarelab.rapidnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dotescapesoftwarelab.rapidnotes.ui.screens.add_edit_note_screen.AddEditNoteScreen
import com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen.HomeScreen
import com.dotescapesoftwarelab.rapidnotes.ui.theme.RapidNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RapidNotesTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RapidNotesTheme {
        Greeting("Android")
    }
}