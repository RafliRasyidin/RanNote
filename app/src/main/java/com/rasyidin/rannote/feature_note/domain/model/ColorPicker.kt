package com.rasyidin.rannote.feature_note.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ColorPicker(
    var colorCard: String = "#ffadad",
    var colorTitle: String = "#b84f4f",
    var colorDesc: String = "#c24c4c"
) : Parcelable
