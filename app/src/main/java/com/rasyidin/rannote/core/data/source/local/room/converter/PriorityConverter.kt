package com.rasyidin.rannote.core.data.source.local.room.converter

import androidx.room.TypeConverter
import com.rasyidin.rannote.core.domain.model.todo.Priority

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