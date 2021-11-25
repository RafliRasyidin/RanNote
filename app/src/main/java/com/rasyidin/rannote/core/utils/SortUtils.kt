package com.rasyidin.rannote.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import com.rasyidin.rannote.core.utils.Constants.DATE_ASC
import com.rasyidin.rannote.core.utils.Constants.DATE_DESC
import com.rasyidin.rannote.core.utils.Constants.TITLE_ASC
import com.rasyidin.rannote.core.utils.Constants.TITLE_DESC

object SortUtils {

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM note ")
        when (filter) {
            DATE_DESC -> simpleQuery.append("ORDER BY date DESC")
            DATE_ASC -> simpleQuery.append("ORDER BY date ASC")
            TITLE_DESC -> simpleQuery.append("ORDER BY title DESC")
            TITLE_ASC -> simpleQuery.append("ORDER BY title ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}