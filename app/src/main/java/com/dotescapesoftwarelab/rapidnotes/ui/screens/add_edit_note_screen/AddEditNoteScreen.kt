package com.dotescapesoftwarelab.rapidnotes.ui.screens.add_edit_note_screen

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotescapesoftwarelab.rapidnotes.R
import com.dotescapesoftwarelab.rapidnotes.domain.model.Note
import com.dotescapesoftwarelab.rapidnotes.ui.screens.add_edit_note_screen.components.TransientTextField
import com.dotescapesoftwarelab.rapidnotes.ui.theme.*
import com.dotescapesoftwarelab.rapidnotes.ui.utils.UiEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                is UiEvent.NavigateTo -> {
                    // TODO: 26-Mar-22
                }
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val titleTextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )
    val noteTextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )
    val focusManager = LocalFocusManager.current
    val backgroundAnimatable = remember{
        Animatable(
            viewModel.backgroundColor
        )
    }

    Scaffold(
        floatingActionButton = {
            val saveIcon = painterResource(R.drawable.ic_save)
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditNoteEvent.OnSaveButtonPressed) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    painter = saveIcon,
                    contentDescription = "Save note"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundAnimatable.value),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        ){
            /*item{
                Text(
                    text = "Rapid Notes",
                    modifier = Modifier.focusable(enabled = true)
                )
                Spacer(Modifier.height(16.dp))
            }*/
            item{
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Note.noteColors.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .shadow(15.dp, CircleShape)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = 2.dp,
                                    color = if (color == viewModel.backgroundColor) Color.Black else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable {
                                    coroutineScope.launch {
                                        backgroundAnimatable.animateTo(
                                            targetValue = color,
                                            animationSpec = tween(
                                                durationMillis = 400,
                                            )
                                        )
                                    }
                                    viewModel.onEvent(
                                        AddEditNoteEvent.OnBackgroundColorChanged(
                                            color
                                        )
                                    )
                                }
                        )
                    }
                }
            }
            item{
                Spacer(Modifier.height(20.dp))
            }
            item {
                /*Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopStart
                ) {
                    BasicTextField(
                        value = title,
                        onValueChange = { title = it },
                        modifier = Modifier
                            .onFocusChanged {
                                            titleHintState = !it.isFocused && title.isBlank()
                            },
                        textStyle = titleTextStyle,
                        singleLine = true,
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        )
                    )
                    if(titleHintState){
                        Text(
                            text = "Title",
                            style = titleTextStyle,
                            color = Color.DarkGray
                        )
                    }
                }*/
                TransientTextField(
                    text = viewModel.title,
                    hint = "Title",
                    onValueChanged = {
                        viewModel.onEvent(AddEditNoteEvent.OnTitleChanged(it))
                    },
                    focusManager = focusManager,
                    singleLine = true,
                    textStyle = titleTextStyle,
                    isHintVisible = viewModel.titleHintState,
                )
                Spacer(Modifier.height(8.dp))
            }
            item{
                TransientTextField(
                    text = viewModel.content,
                    hint = "Note",
                    onValueChanged = {
                        viewModel.onEvent(AddEditNoteEvent.OnContentChanged(it))
                    },
//                    onFocusChanged = { noteHintState = !it.isFocused && note.isBlank() },
                    focusManager = focusManager,
                    textStyle = noteTextStyle,
                    isHintVisible = viewModel.contentHintState,
                )
            }
            /*item{
                Button(
                    onClick = {
                        coroutineScope.launch {
                            backgroundAnimatable.animateTo(
                                targetValue = BabyBlue,
                                animationSpec = tween(
                                    durationMillis = 400
                                )
                            )
                        }
                    }
                ){
                    Text(
                        text = "Click me!"
                    )
                }
            }*/
        }
    }
}