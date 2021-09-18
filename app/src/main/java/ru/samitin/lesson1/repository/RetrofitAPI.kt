package ru.samitin.lesson1.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface RetrofitAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PODServerResponseData>

    @GET("DONKI/FLR")
    fun getSolarFlair(
        @Query("api_key") apiKey: String,
        @Query("startDate") startDate : String="2021-09-01",
        @Query("endDate") endDate :String="2021-09-30"
    ): Call<List<SolarFlairResponseData>>

    @GET("DONKI/FLR")
    fun getSolarFlairToday(
        @Query("api_key") apiKey: String,
        @Query("startDate") startDate : String="2021-09-07",
    ): Call<List<SolarFlairResponseData>>
}
