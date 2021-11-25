package com.rasyidin.rannote.core.utils

import com.rasyidin.rannote.core.domain.model.note.ColorPicker

object Constants {

    const val DATE_DESC = "DateDesc"
    const val DATE_ASC = "DateAsc"
    const val TITLE_DESC = "TitleDesc"
    const val TITLE_ASC = "TitleAsc"

    const val DATABASE_NAME = "ran_note.db"

    val colorTheme = arrayListOf(
        // RED
        ColorPicker(
            "#ffadad",
            "#b84f4f",
            "#c24c4c"
        ),
        // ORANGE
        ColorPicker(
            "#ffd6a5",
            "#b8884f",
            "#c28d4e"
        ),
        // YELLOW
        ColorPicker(
            "#fdffb6",
            "#b5b84f",
            "#bfc24e"
        ),
        //GREEN
        ColorPicker(
            "#caffbf",
            "#61b84f",
            "#62c24e"
        ),
        // BLUE
        ColorPicker(
            "#9bf6ff",
            "#4faeb8",
            "#4eb7c2"
        ),
        //DARK BLUE
        ColorPicker(
            "#a0c4ff",
            "#4f77b8",
            "#4e7ac2"
        ),
        //PURPLE
        ColorPicker(
            "#bdb2ff",
            "#5e4fb8",
            "#5e4ec2"
        ),
        //PINK
        ColorPicker(
            "#ffc6ff",
            "#b84fb8",
            "#c24ec2"
        ),
        //WHITE
        ColorPicker(
            "#fffffc",
            "#FF000000",
            "#2E2D2D"
        ),
    )
}