package com.rasyidin.rannote.feature_note.domain.util

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val DATE_DESC = "DateDesc"
    const val DATE_ASC = "DateAsc"
    const val TITLE_DESC = "TitleDesc"
    const val TITLE_ASC = "TitleAsc"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM note ")
        when (filter) {
            DATE_DESC -> simpleQuery.append("ORDER BY date DESC")
            DATE_ASC -> simpleQuery.append("ORDER BY date ASC")
            TITLE_DESC -> simpleQuery.append("ORDER BY title COLLATE NOCASE DESC")
            TITLE_ASC -> simpleQuery.append("ORDER BY title COLLATE NOCASE ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}