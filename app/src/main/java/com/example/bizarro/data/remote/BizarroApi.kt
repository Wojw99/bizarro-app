package com.example.bizarro.data.remote

import com.example.bizarro.data.remote.responses.Record
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BizarroApi {
    @GET("records")
    suspend fun getRecordList(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("name") name: String?,
        @Query("city") city: String?,
        @Query("province") province: String?,
        @Query("type") type: String?,
    ): List<Record>

    @GET("records/{recordId}")
    suspend fun getRecordDetails(
        @Path("recordId") recordId: Long
    ): Record
}