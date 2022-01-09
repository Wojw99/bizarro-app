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
        @Query("rentalPeriod") rentalPeriod: Int?,

        @Body image: MultipartBody,
    ): Record

    @DELETE("/post/{recordId}")
    suspend fun deleteRecord(
        @Header("Authorization") authHeader: String,
        @Path("recordId") recordId: Long,
    ): String

    @PATCH("/post/{recordId}")
    suspend fun updateRecord(
        @Header("Authorization") authHeader: String,
        @Path("recordId") recordId: Long,
        @Body updateRecord: UpdateRecord,
    ): String

//    curl -X 'POST' \
//    'https://bike-app-1.herokuapp.com/search_post/?min_price=1000&max_price=4000' \
//    -H 'accept: application/json' \
//    -d ''

    @POST("/search_post")
    suspend fun gerFilteredRecordList(
        @Query("title") title: String?,
        @Query("tape_of_service") type: String?,
        @Query("category_of_bike") category: String?,
        @Query("min_price") minPrice: Double?,
        @Query("max_price") maxPrice: Double?,
        @Query("address_province") province: String?,
        @Query("swapObject") swapObject: String?,
        @Query("rentalPeriod") rentalPeriod: Int?,
    ): List<Record>

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