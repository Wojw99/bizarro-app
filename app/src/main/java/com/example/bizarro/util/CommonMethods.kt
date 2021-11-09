package com.example.bizarro.util

import java.time.LocalDate

object CommonMethods {
    fun convertToPriceFormat(number: Double) : String{
        var str = number.toString()
        str += "zł"
        str = str.replace('.', ',')
        return str
    }

    fun convertToRecordBoxDateFormat(date: LocalDate) : String{
        var monthValue = date.monthValue.toString()
        var dayOfMonth = date.dayOfMonth.toString()

        if(monthValue.length < 2) monthValue = "0$monthValue"
        if(dayOfMonth.length < 2) dayOfMonth = "0$dayOfMonth"

        return "$dayOfMonth.$monthValue"
    }
}