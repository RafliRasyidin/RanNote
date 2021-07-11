package com.rasyidin.rannote.core.domain.model.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Int = 0,
    val title: String? = "",
    val desc: String? = "",
    val color: String = "",
    val date: String? = ""
) : Parcelable
