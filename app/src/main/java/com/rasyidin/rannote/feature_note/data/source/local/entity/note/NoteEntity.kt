package com.rasyidin.rannote.feature_note.data.source.local.entity.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "description")
    val desc: String? = "",

    @ColumnInfo(name = "color_card")
    val colorCard: String = "",

    @ColumnInfo(name = "color_title")
    val colorTitle: String = "",

    @ColumnInfo(name = "color_desc")
    val colorDesc: String = "",

    @ColumnInfo(name = "date")
    val date: String? = ""
)
