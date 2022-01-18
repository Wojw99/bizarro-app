package com.example.bizarro

import com.example.bizarro.utils.ValidationHelper
import org.junit.Assert
import org.junit.Test

class ValidationHelperTest {
    @Test
    fun validateRegisterFieldsTest(){
        val tested = listOf("username", "woj@test.pl", "zaq12wsx", "zaq12wsx")
        val unexpected = ""
        val actual = ValidationHelper.validateRegisterFields(tested[0], tested[1], tested[2], tested[3])
        Assert.assertEquals(unexpected, actual)
    }

    @Test
    fun validateRegisterFieldsTest2(){
        val tested = listOf("username", "woj@test.pl", "NNNN", "zaq12wsx")
        val unexpected = ""
        val actual = ValidationHelper.validateRegisterFields(tested[0], tested[1], tested[2], tested[3])
        Assert.assertNotEquals(unexpected, actual)
    }

    @Test
    fun validateRegisterFieldsTest3(){
        val tested = listOf("", "woj@test.pl", "zaq12wsx", "zaq12wsx")
        val unexpected = ""
        val actual = ValidationHelper.validateRegisterFields(tested[0], tested[1], tested[2], tested[3])
        Assert.assertNotEquals(unexpected, actual)
    }

    @Test
    fun validateEmailTest1() {
        val tested = "woj@test.pl"
        val expected = ""
        val actual = ValidationHelper.validateEmail(tested)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun validateEmailTest2() {
        val tested = "woj@test."
        val unexpected = ""
        val actual = ValidationHelper.validateEmail(tested)
        Assert.assertNotEquals(unexpected, actual)
    }

    @Test
    fun validatePasswordAndCodeTest1() {
        val tested = listOf("345asdfada", "zaq12wsx", "zaq12wsx__")
        val unexpected = ""
        val actual = ValidationHelper.validatePasswordAndCode(tested[0], tested[1], tested[2])
        Assert.assertNotEquals(unexpected, actual)
    }

    @Test
    fun validatePasswordAndCodeTest2() {
        val tested = listOf("345asdfada", "zaq12wsx", "zaq12wsx")
        val expected = ""
        val actual = ValidationHelper.validatePasswordAndCode(tested[0], tested[1], tested[2])
        Assert.assertEquals(expected, actual)
    }
}