package com.dotescapesoftwarelab.rapidnotes.ui.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dotescapesoftwarelab.rapidnotes.domain.model.Note

@Composable
fun NoteItem(
    note: Note,
    onDeleteClick: () -> Unit,
    onItemClick: () -> Unit
) {

    val cornerRadius = 8.dp
    val contentColor = Color.Black

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color(note.color))
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clickable {
                onItemClick()
            }
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
        ){
            if(note.title.isNotBlank()){
                Text(
                    text = note.title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = contentColor
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if(note.title.isNotBlank() && note.content.isNotBlank()){
                Spacer(Modifier.height(4.dp))
            }
            if(note.content.isNotBlank()) {
                Text(
                    text = note.content,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = contentColor
                    ),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Note",
                tint = contentColor
            )
        }
    }
}