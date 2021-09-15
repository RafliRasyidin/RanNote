package com.rasyidin.rannote.core.domain.model.todo

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
}

sealed class Category {
    object Work : Category()
    object Family : Category()
    object School : Category()
}

sealed class Priority {
    object Low : Priority()
    object Medium : Priority()
    object High : Priority()
}
