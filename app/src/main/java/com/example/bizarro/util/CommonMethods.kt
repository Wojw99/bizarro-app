package com.example.bizarro.util

import androidx.compose.ui.text.substring
import androidx.compose.ui.text.toLowerCase
import timber.log.Timber
import java.time.LocalDate
import java.util.*

object CommonMethods {
    fun formatRecordTypeText(text: String) : String {
        val lowerCase = text.lowercase(Locale.getDefault())
        val firstLetter = lowerCase[0].uppercaseChar()
        return firstLetter + lowerCase.substring(1)
    }

    fun convertToPriceFormat(number: Double) : String{
        var str = number.toString()
        str += "zł"
        str = str.replace('.', ',')

        return str
    }

    fun convertToRecordBoxDateFormat(date: LocalDate) : String{
        var monthValue = date.monthValue.toString()
        var dayOfMonth = date.dayOfMonth.toString()
        val year = date.year.toString()

        val dateNow = LocalDate.now()
        val diff = dateNow.minusDays(date.toEpochDay()).toEpochDay()
        if(diff == 1L) return "wczoraj"
        else if(diff == 0L) return "dzisiaj"

        if(monthValue.length < 2) monthValue = "0$monthValue"
        if(dayOfMonth.length < 2) dayOfMonth = "0$dayOfMonth"

        if(dateNow.year != date.year)
            return "$dayOfMonth.$monthValue.$year"

        return "$dayOfMonth.$monthValue"
    }
}