package com.example.bizarro.data.remote

import com.example.bizarro.data.remote.responses.Record
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BizarroApi {
    @GET("records")
    suspend fun getRecordList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): List<Record>

    @GET("records/{recordId}")
    suspend fun getRecordDetails(
        @Path("recordId") recordId: Long
    ): Record
}