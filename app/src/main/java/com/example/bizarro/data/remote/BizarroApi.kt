package com.example.bizarro.data.remote

import com.example.bizarro.data.remote.responses.Opinion
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.data.remote.responses.UserProfile
import retrofit2.http.*

interface BizarroApi {
    // * * * * * GET * * * * *
    @GET("records")
    suspend fun getRecordList(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("name") name: String?,
        @Query("city") city: String?,
        @Query("province") province: String?,
        @Query("type") type: String?,
        @Query("category") category: String?,
    ): List<Record>

    @GET("records/{recordId}")
    suspend fun getRecordDetails(
        @Path("recordId") recordId: Long
    ): Record

    @GET("/api/users/{userId}/profile")
    suspend fun getUserProfile(
        @Path("userId") userId: Long
    ): UserProfile

    @GET("/api/users/{userId}/opinions")
    suspend fun getUserOpinions(
        @Path("userId") userId: Long
    ): List<Opinion>

    // * * * * * POST * * * * *
    @POST("/api/opinions")
    suspend fun addUserOpinion(
        @Body opinion: Opinion,
    ): Opinion

    // * * * * * PUT * * * * *

    // * * * * * DELETE * * * * *
}