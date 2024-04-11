package com.coolgirl.madmeditation.Models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiController {
    @Headers("Accept: application/json")
    @POST("user/login")
    fun autorizeClient(@Body clientData:UserLoginData): Call<UserLoginDataResponse>?

    @GET("feelings")
    fun getFeelings(): Call<ResponseFeelings>?
}