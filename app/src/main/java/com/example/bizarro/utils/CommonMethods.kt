package com.example.bizarro.utils

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.text.TextUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDate
import java.util.*

object CommonMethods {
    fun isValidEmail(email: String): Boolean{
        if (TextUtils.isEmpty(email)) {
            return false
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    fun convertEmptyStringToNull(string: String?): String? {
        if (string != null && string.isEmpty()) {
            return null
        }
        return string
    }

    fun convertNullToEmptyString(string: String?): String {
        if (string == null) {
            return ""
        }
        return string
    }

    fun getDescriptionForCategory(categoryName: String?): String {
        for(x in Constants.categories) {
            if(categoryName == x.name) return x.description
        }
        return Strings.noCategoryDesc
    }

    fun getDescriptionForNumberMark(numberMark: Double?): String {
        if (numberMark == null) return Strings.noMarkDesc
        if (numberMark > 2.5) return Strings.positiveOpinions
        if (numberMark < 2.5) return Strings.negativeOpinions
        return Strings.noMarkDesc
    }

    fun getUrlForImage(imageUrl: String): String {
        return "https${imageUrl.substring(4)}"
    }

    fun formatRecordTypeText(text: String): String {
        val lowerCase = text.lowercase(Locale.getDefault())
        val firstLetter = lowerCase[0].uppercaseChar()
        return firstLetter + lowerCase.substring(1)
    }

    fun convertToPriceFormat(number: Double?): String {
        if (number == null) return Strings.undefined

        var str = number.toString()
        str += "zł"

        return str
    }

    fun convertToRentalPeriodFormat(number: Int?): String {
        if (number == null) return Strings.undefined
        return "$number${Strings.days}"
    }

    fun convertToSwapObjectFormat(swapObject: String?): String {
        if (swapObject == null) return Strings.undefined
        return swapObject
    }

    fun convertToRecordBoxDateFormat(date: LocalDate): String {
        var monthValue = date.monthValue.toString()
        var dayOfMonth = date.dayOfMonth.toString()
        val year = date.year.toString()

        val dateNow = LocalDate.now()
        val diff = dateNow.minusDays(date.toEpochDay()).toEpochDay()
        if (diff == 1L) return "wczoraj"
        else if (diff == 0L) return "dzisiaj"

        if (monthValue.length < 2) monthValue = "0$monthValue"
        if (dayOfMonth.length < 2) dayOfMonth = "0$dayOfMonth"

        if (dateNow.year != date.year)
            return "$dayOfMonth.$monthValue.$year"

        return "$dayOfMonth.$monthValue"
    }

    fun convertToLabelDateFormat(date: LocalDate): String {
        val monthValue = date.monthValue.toString()
        val dayOfMonth = date.dayOfMonth.toString()
        val year = date.year.toString()

        return "$dayOfMonth.$monthValue.$year"
    }

    fun convertBitmapToFile(bitmap: Bitmap, context: Context): File {
        val wrapper = ContextWrapper(context)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file
    }
}