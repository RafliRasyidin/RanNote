package com.rasyidin.rannote.ui.helper

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Singleton
fun getCurrentDate(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}

