package com.example.bizarro.api

import com.example.bizarro.api.models.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface BizarroApi {
    // * * * * * OTHER * * * * *
    @FormUrlEncoded
    @POST("token")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Token

    // * * * * * USER * * * * *
    @GET("/users/me")
    suspend fun getUserMe(
        @Header("Authorization") authHeader: String,
    ): UserProfile

    @GET("/user/{userId}")
    suspend fun getOtherUser(
        @Path("userId") userId: Long,
    ): UserDetails

    @POST("/users/")
    suspend fun createUser(
        @Body addUser: AddUser
    ): UserProfile

    @PATCH("/update_user")
    suspend fun updateUser(
        @Header("Authorization") authHeader: String,
        @Body updateUserProfile: UpdateUserProfile,
    ): UpdateUserProfile

    @POST("/add_user_photo/")
    suspend fun addUserPhoto(
        @Header("Authorization") authHeader: String,
        @Query("user_id") userId: Long,
        @Body image: MultipartBody,
    ): String

    @POST("/email/{to_email}")
    suspend fun sendResetPasswordRequest(
        @Path("to_email") toEmail: String,
    ): ResetPasswordCode

    @PATCH("/reset_password/")
    suspend fun resetPassword(
        @Body resetPassword: ResetPassword,
    ): String

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

    // * * * * * OPINIONS * * * * *
    @POST("/user/{userId}/comment")
    suspend fun createOpinion(
        @Header("Authorization") authHeader: String,
        @Path("userId") userId: Long,
        @Body addOpinion: AddOpinion,
        @Query("mark") mark: Int,
    ): Opinion

    @POST("/user_comments/{userId}")
    suspend fun getUserWithOpinions(
        @Header("Authorization") authHeader: String,
        @Path("userId") userId: Long,
    ): UserWithOpinions
}