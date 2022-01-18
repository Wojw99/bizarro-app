package com.example.bizarro

import com.example.bizarro.utils.Resource
import org.junit.Assert
import org.junit.Test

class ResourceTest {
    @Test
    fun resourceTest1() {
        // ERROR TESTING
        val tested = Resource.Error<String>(message = "Error!")
        val unexpected = null
        val actual = tested.message
        Assert.assertNotEquals(unexpected, actual)
    }

    @Test
    fun resourceTest2() {
        // SUCCESS TESTING
        val tested = Resource.Success<String>(data = "data")
        val unexpected = null
        val actual = tested.data
        Assert.assertNotEquals(unexpected, actual)
    }
}