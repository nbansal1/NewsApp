package com.codingwithnaman.newsapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDate(dateToFormat : String?) : String {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        val date = LocalDate.parse(dateToFormat, format)
        return date.toString()
    }
}