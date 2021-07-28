package com.rasyidin.rannote.core.domain.model.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    var id: Int = 0,
    var title: String? = "",
    var desc: String? = "",
    var colorCard: String = "",
    var colorTitle: String = "",
    var colorDesc: String = "",
    var date: String? = ""
) : Parcelable
