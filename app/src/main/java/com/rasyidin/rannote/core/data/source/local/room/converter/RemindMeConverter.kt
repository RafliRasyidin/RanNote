package com.rasyidin.rannote.core.data.source.local.room.converter

import androidx.room.TypeConverter
import com.rasyidin.rannote.core.domain.model.todo.RemindMe

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