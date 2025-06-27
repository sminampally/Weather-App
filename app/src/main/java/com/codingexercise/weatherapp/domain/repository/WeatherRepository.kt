package com.codingexercise.weatherapp.domain.repository

import com.codingexercise.weatherapp.data.remote.ForecastItem

interface WeatherRepository {
    suspend fun getWeatherForecast(city: String): List<ForecastItem>
}