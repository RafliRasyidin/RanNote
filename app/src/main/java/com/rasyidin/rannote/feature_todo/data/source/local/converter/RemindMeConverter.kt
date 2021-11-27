package com.rasyidin.rannote.feature_todo.data.source.local.converter

import androidx.room.TypeConverter
import com.rasyidin.rannote.feature_todo.domain.model.RemindMe

class RemindMeConverter {

    @TypeConverter
    fun toRemindMe(value: Boolean): RemindMe {
        return if (value) {
            RemindMe.Active
        } else {
            RemindMe.Disable
        }
    }

    @TypeConverter
    fun fromRemindMe(value: RemindMe): Boolean {
        return when (value) {
            is RemindMe.Disable -> false
            is RemindMe.Active -> true
        }
    }
}