package com.okky.ezhousing.api

import com.okky.ezhousing.api.response.LoginUserResponse
import com.okky.ezhousing.api.response.TanahResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("stories")
    fun getAllTanahWithLocation(
        @Header("Authorization") authorization: String,
        @Query("location") location: Int = 1
    ) : Call<TanahResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<LoginUserResponse>
}