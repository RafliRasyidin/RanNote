package com.rasyidin.rannote.core.data.source.local.entity.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "description")
    val desc: String? = "",

    @ColumnInfo(name = "color")
    val color: String? = "",

    @ColumnInfo(name = "date")
    val date: String = ""
)
