package com.example.bizarro

import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CommonMethodsTest {
    @Test
    fun isValidEmailTest() {
        val email = "woj1999@o2.pl"
        val expected = true
        val actual = CommonMethods.isValidEmail(email)
        assertEquals(expected, actual)
    }

    @Test
    fun isValidEmailTest2() {
        val email = "bizarro@gmail.com"
        val expected = true
        val actual = CommonMethods.isValidEmail(email)
        assertEquals(expected, actual)
    }

    @Test
    fun isValidEmailTest3() {
        val email = "bizarro.pl"
        val expected = false
        val actual = CommonMethods.isValidEmail(email)
        assertEquals(expected, actual)
    }

    @Test
    fun isValidEmailTest4() {
        val email = "bizarro@com"
        val expected = false
        val actual = CommonMethods.isValidEmail(email)
        assertEquals(expected, actual)
    }

    @Test
    fun convertEmptyStringToNullTest() {
        val empty = ""
        val expected = null
        val actual = CommonMethods.convertEmptyStringToNull(empty)
        assertEquals(expected, actual)
    }

    @Test
    fun convertEmptyStringToNullTest2() {
        val empty = "test test"
        val expected = "test test"
        val actual = CommonMethods.convertEmptyStringToNull(empty)
        assertEquals(expected, actual)
    }

    @Test
    fun convertNullToEmptyStringTest() {
        val tested = null
        val expected = ""
        val actual = CommonMethods.convertNullToEmptyString(tested)
        assertEquals(expected, actual)
    }

    @Test
    fun convertNullToEmptyStringTest2() {
        val tested = "Test test"
        val expected = "Test test"
        val actual = CommonMethods.convertNullToEmptyString(tested)
        assertEquals(expected, actual)
    }

    @Test
    fun getDescriptionForCategoryTest() {
        val tested = Constants.categories[4].name
        val expected = Constants.categories[4].description
        val actual = CommonMethods.getDescriptionForCategory(tested)
        assertEquals(expected, actual)
    }

    @Test
    fun getUrlForImageTest() {
        val tested = "http://stackoverflow.com"
        val expected = "https://stackoverflow.com"
        val actual = CommonMethods.getUrlForImage(tested)
        assertEquals(expected, actual)
    }

    @Test
    fun formatRecordTypeTextTest() {
        val tested = "sprzedam"
        val expected = "Sprzedam"
        val actual = CommonMethods.formatRecordTypeText(tested)
        assertEquals(expected, actual)
    }

    @Test
    fun convertToPriceFormatTest(){
        val tested = 20.0
        val expected = "20.0zł"
        val actual = CommonMethods.convertToPriceFormat(tested)
        assertEquals(expected, actual)
    }

    @Test
    fun convertToRentalPeriodFormatTest() {
        val tested = 20
        val expected = "20dni"
        val actual = CommonMethods.convertToRentalPeriodFormat(tested)
        assertEquals(expected, actual)
    }

    @Test
    fun convertToRecordBoxDateFormatTest() {
        val tested = LocalDate.of(1999, 4, 21)
        val expected = "21.04.1999"
        val actual = CommonMethods.convertToRecordBoxDateFormat(tested)
        assertEquals(expected, actual)
    }

    @Test
    fun convertToLabelDateFormatTest(){
        val tested = LocalDate.of(1999, 4, 21)
        val expected = "21.4.1999"
        val actual = CommonMethods.convertToLabelDateFormat(tested)
        assertEquals(expected, actual)
    }
}