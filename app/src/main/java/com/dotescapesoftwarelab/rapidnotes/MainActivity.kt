package com.dotescapesoftwarelab.rapidnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dotescapesoftwarelab.rapidnotes.ui.screens.add_edit_note_screen.AddEditNoteScreen
import com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen.HomeScreen
import com.dotescapesoftwarelab.rapidnotes.ui.theme.RapidNotesTheme
import com.dotescapesoftwarelab.rapidnotes.ui.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            RapidNotesTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ){
                    composable(
                        route = Screen.Home.route
                    ){
                        HomeScreen(navController = navController)
                    }
                    composable(
                        route = Screen.AddEditNote.route + "?noteId={noteId}",
                        arguments = listOf(
                            navArgument(
                                name = "noteId",
                            ){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        AddEditNoteScreen(navController = navController)
                    }
                }
            }
        }
    }
}
