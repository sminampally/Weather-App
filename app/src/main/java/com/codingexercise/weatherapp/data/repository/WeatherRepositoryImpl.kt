package com.codingexercise.weatherapp.data.repository

import com.codingexercise.weatherapp.data.remote.ForecastItem
import com.codingexercise.weatherapp.data.remote.WeatherApiService
import com.codingexercise.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeatherForecast(city: String): List<ForecastItem> {
        val response = api.getForecast(city)
        if (response.isSuccessful) {
            return response.body()?.list ?: emptyList()
        }

        when (response.code()) {
            400 -> throw Exception("Invalid city name. Please try again.")
            404 -> throw Exception("City not found.")
            500 -> throw Exception("Server error. Please try later.")
            else -> throw Exception("Network error: ${response.message()}")
        }
    }
}
