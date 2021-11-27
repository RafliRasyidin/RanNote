package com.rasyidin.rannote.feature_todo.data.source.local.converter

import androidx.room.TypeConverter
import com.rasyidin.rannote.feature_todo.domain.model.Category

class CategoryConverters {

    @TypeConverter
    fun toCategory(value: Int): Category {
        return when (value) {
            1 -> Category.Work
            2 -> Category.Family
            else -> Category.School
        }
    }

    @TypeConverter
    fun fromCategory(category: Category): Int {
        return when (category) {
            is Category.Work -> 1
            is Category.Family -> 2
            is Category.School -> 3
        }
    }
}