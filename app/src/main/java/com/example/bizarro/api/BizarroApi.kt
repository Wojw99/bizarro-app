package com.example.bizarro.api

import com.example.bizarro.api.models.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface BizarroApi {
    // * * * * * DEFAULT * * * * *
    @FormUrlEncoded
    @POST("token")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Token

    // * * * * * USER * * * * *
    @GET("/users/me")
    suspend fun getUserProfile(): UserProfile

    // * * * * * POSTS * * * * *
    @GET("/user_posts")
    suspend fun getUserRecords(
        @Header("Authorization") authHeader: String,
    ): List<Record>

    @GET("/post_list")
    suspend fun getAllRecords(): List<Record>

    @GET("/post/{recordId}")
    suspend fun getRecordDetails(
        @Path("recordId") recordId: Long
    ): RecordDetails

    @POST("/posts/")
    suspend fun createRecord(
        @Header("Authorization") authHeader: String,

        @Query("title") title: String,
        @Query("description") body: String,
        @Query("tape_of_service") type: String,
        @Query("category_of_bike") category: String,
        @Query("address_province") addressProvince: String,
        @Query("address_city") addressCity: String,
        @Query("address_street") addressStreet: String,
        @Query("address_number") addressNumber: String,

        @Query("price") price: Double?,
        @Query("swapObject") swapObject: String?,
        @Query("rentalPeriod") rentalPeriod: Double?,

        @Body image: MultipartBody,
    ): Record

    // * * * * * OLD * * * * *
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

    @POST("/api/records")
    suspend fun addRecord(
        @Body record: Record,
    ): Record

    @GET("/api/users/{userId}/records")
    suspend fun getUserRecords(
        @Path("userId") userId: Long
    ): List<Record>

    @POST("/api/opinions")
    suspend fun addUserOpinion(
        @Body opinion: Opinion,
    ): Opinion

    @GET("/api/users/{userId}/opinions")
    suspend fun getUserOpinions(
        @Path("userId") userId: Long
    ): List<Opinion>

}