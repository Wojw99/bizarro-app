package com.example.bizarro.repositories

import com.example.bizarro.utils.Strings
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException

abstract class NetworkingRepository {
    // TODO: Parse more error responses
    protected fun parseError(e: Exception): String {
        if(e is ConnectException) {
            return Strings.networkError
        } else if (e is HttpException) {
            if (e.code() == 404) {
                return Strings.notFoundError
            }
        }
        return Strings.unknownError
    }
}