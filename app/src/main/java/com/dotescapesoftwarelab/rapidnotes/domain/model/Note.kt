package com.dotescapesoftwarelab.rapidnotes.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dotescapesoftwarelab.rapidnotes.ui.theme.*

@Entity(tableName = "notes")
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null,
){
    companion object {
        val noteColors = listOf(
            Color.White,
            RedOrange,
            LightGreen,
            Violet,
            BabyBlue,
            RedPink
        )
    }
}
