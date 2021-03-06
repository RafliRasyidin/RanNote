package com.rasyidin.rannote.feature_todo.domain.model

data class Todo(
    val todoId: Int = 0,
    val titleTask: String,
    val description: String?,
    val date: String,
    val time: String,
    val notification: RemindMe,
    val priority: Priority,
    val category: Category
)

sealed class RemindMe {
    object Active : RemindMe()
    object Disable : RemindMe()

    override fun toString(): String {
        return when (this) {
            is Active -> "Active"
            is Disable -> "Disable"
        }
    }
}

sealed class Category {
    object Work : Category()
    object Family : Category()
    object School : Category()

    override fun toString(): String {
        return when (this) {
            is Work -> "Work"
            is Family -> "Family"
            is School -> "School"
        }
    }
}

sealed class Priority {
    object Low : Priority()
    object Medium : Priority()
    object High : Priority()

    override fun toString(): String {
        return when (this) {
            is Low -> "Low"
            is Medium -> "Medium"
            is High -> "High"
        }
    }
}
