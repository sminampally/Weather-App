package com.codingexercise.weatherapp.data.remote

data class WeatherResponse(
    val list: List<ForecastItem>
)


data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val dt_txt: String
)

data class Main(
    val temp: Double,
    val feels_like: Double
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)