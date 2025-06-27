package com.codingexercise.weatherapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "65d00499677e59496ca2f318eb68c049",
        @Query("units") units: String = "imperial"
    ): Response<WeatherResponse>
}
