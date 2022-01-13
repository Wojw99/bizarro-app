package com.example.bizarro.repositories

import com.example.bizarro.utils.Strings
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException

abstract class NetworkingRepository {
    protected fun parseError(e: Exception): String {
        if(e is ConnectException) {
            return Strings.networkError
        } else if (e is HttpException) {
            if (e.code() == 404) {
                return Strings.notFoundError
            }
            if (e.code() == 500) {
                return Strings.internalServerError
            }
            if (e.code() == 401) {
                return Strings.error401
            }
        }
        return Strings.unknownError
    }
}