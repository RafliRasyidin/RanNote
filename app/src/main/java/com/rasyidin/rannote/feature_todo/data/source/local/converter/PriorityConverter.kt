package com.rasyidin.rannote.feature_todo.data.source.local.converter

import androidx.room.TypeConverter
import com.rasyidin.rannote.feature_todo.domain.model.Priority

class PriorityConverter {

    @TypeConverter
    fun toPriority(value: Int): Priority {
        return when (value) {
            1 -> Priority.Low
            2 -> Priority.Medium
            else -> Priority.High
        }
    }

    @TypeConverter
    fun fromPriority(priority: Priority): Int {
        return when (priority) {
            is Priority.Low -> 1
            is Priority.Medium -> 2
            is Priority.High -> 3
        }
    }
}