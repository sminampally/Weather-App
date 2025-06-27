package com.codingexercise.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingexercise.weatherapp.data.remote.ForecastItem
import com.codingexercise.weatherapp.domain.usecase.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
) : ViewModel() {

    private val _forecastList = MutableStateFlow<List<ForecastItem>>(emptyList())
    val forecastList: StateFlow<List<ForecastItem>> = _forecastList

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    private val _navigateToList = MutableStateFlow(false)
    val navigateToList: StateFlow<Boolean> = _navigateToList

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            try {
                _error.value = "" // reset error before fetch
                _forecastList.value = getWeatherForecastUseCase(city)
                _navigateToList.value = true

            } catch (e: HttpException) {
                if (e.code() == 400 || e.code() == 404 || e.code() == 500 || e.code() == 404) {
                    _error.value = "Bad request: Invalid city name or parameters."
                    _navigateToList.value = false

                } else {
                    _error.value = "Error ${e.code()}: ${e.message()}"
                    _navigateToList.value = false

                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unknown error"
                _navigateToList.value = false

            }
        }
    }

    fun resetNavigation() {
        _navigateToList.value = false
    }
}