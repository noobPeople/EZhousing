package com.okky.ezhousing.api

import com.okky.ezhousing.api.response.TanahResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("stories")
    fun getAllTanahWithLocation(
        @Header("Authorization") authorization: String,
        @Query("location") location: Int = 1
    ) : Call<TanahResponse>
}