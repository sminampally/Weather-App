package com.codingexercise.weatherapp.domain.usecase

import com.codingexercise.weatherapp.data.remote.ForecastItem
import com.codingexercise.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): List<ForecastItem> {
        return repository.getWeatherForecast(city)
    }
}