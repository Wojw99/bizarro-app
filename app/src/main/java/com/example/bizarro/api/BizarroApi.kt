package com.example.bizarro.api

import com.example.bizarro.api.models.Opinion
import com.example.bizarro.api.models.Record
import com.example.bizarro.api.models.UserProfile
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

    @GET("/api/users/{userId}/records")
    suspend fun getUserRecords(
        @Path("userId") userId: Long
    ): List<Record>

    // * * * * * POST * * * * *
    @POST("/api/opinions")
    suspend fun addUserOpinion(
        @Body opinion: Opinion,
    ): Opinion

    // * * * * * PUT * * * * *

    // * * * * * DELETE * * * * *
}