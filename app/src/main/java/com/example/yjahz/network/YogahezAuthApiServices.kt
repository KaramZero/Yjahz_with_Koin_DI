package com.example.yjahz.network

import com.example.yjahz.model.user.UserResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface YogahezAuthApiServices {

    @GET(Keys.ClientProfileEndPoint)
    suspend fun getClientProfile(@HeaderMap headers: Map<String, String>): UserResponse


    @POST(Keys.LoginEndPoint)
    suspend fun logIn(@Body requestBody: RequestBody): UserResponse

    @POST(Keys.SignUpEndPoint)
    suspend fun signUp(@HeaderMap headers: Map<String, String>,@Body requestBody: RequestBody): UserResponse

}