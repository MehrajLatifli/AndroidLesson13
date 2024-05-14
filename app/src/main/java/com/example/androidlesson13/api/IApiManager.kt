package com.example.androidlesson13.api

import com.example.androidlesson13.models.LoginRequest
import com.example.androidlesson13.models.LoginResponse
import com.example.androidlesson13.models.UserResponse
import com.example.androidlesson13.models.UserResponseItem
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IApiManager {

    @POST("auth/login")
    fun loginUserApi(@Body loginRequestModel: LoginRequest): retrofit2.Call<LoginResponse>

    @FormUrlEncoded
    fun loginUserApi2(@Field("username") username:String, @Field("password") password:String): retrofit2.Call<LoginResponse>

    @GET("users")
    fun getAllUserApi(@Query("limit") limit: String?): retrofit2.Call<List<UserResponseItem>>
}