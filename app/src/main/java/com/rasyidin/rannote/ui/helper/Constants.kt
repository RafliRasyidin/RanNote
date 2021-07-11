package com.rasyidin.rannote.ui.helper

import com.rasyidin.rannote.R
import com.rasyidin.rannote.core.domain.model.ItemDialog

object Constants {

    const val SPLASH_DELAY = 3000L

    const val ON_BOARDING_PREF_KEY = "OnBoarding"

    const val NAME_PREF = "NamePref"

    const val IS_ALREADY_ON_BOARDING = "AlreadyOnBoarding"

    val ITEMS_ADD_DIALOG = arrayOf(
        ItemDialog("Add Note", R.drawable.ic_note),
        ItemDialog("Add Todo", R.drawable.ic_to_do),
    )

}