package com.codingexercise.weatherapp.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codingexercise.weatherapp.data.remote.ForecastItem
import com.codingexercise.weatherapp.presentation.theme.WeatherAppTheme
import com.codingexercise.weatherapp.presentation.viewmodel.WeatherViewModel
import com.codingexercise.weatherapp.presentation.views.ForecastDetailScreen
import com.codingexercise.weatherapp.presentation.views.ForecastListScreen
import com.codingexercise.weatherapp.presentation.views.SearchScreen
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {

                val navController = rememberNavController()
                val viewModel: WeatherViewModel = hiltViewModel()
                val error by viewModel.error.collectAsState()
                val navigateToList by viewModel.navigateToList.collectAsState()
                var cityToSearch by remember { mutableStateOf("") }

                NavHost(navController, startDestination = "search") {

                    composable("search") {
                        SearchScreen(
                            onLookup = { city ->
                                cityToSearch = city
                                viewModel.fetchWeather(city)
                            },
                            error = error
                        )
                        if (navigateToList) {
                            LaunchedEffect(Unit) {
                                navController.navigate("list/$cityToSearch")
                                viewModel.resetNavigation()
                            }
                        }
                    }

                    composable(
                        "list/{city}",
                        arguments = listOf(navArgument("city") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val city = backStackEntry.arguments?.getString("city") ?: ""
                        val forecastList = viewModel.forecastList.collectAsState().value

                        ForecastListScreen(
                            city = city,
                            forecastList = forecastList,
                            onItemClick = { forecast ->
                                val json = Gson().toJson(forecast)
                                navController.navigate("detail/$city/$json")
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }

                    composable(
                        "detail/{city}/{forecastJson}",
                        arguments = listOf(
                            navArgument("city") { type = NavType.StringType },
                            navArgument("forecastJson") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val city = backStackEntry.arguments?.getString("city") ?: ""
                        val forecastJson = backStackEntry.arguments?.getString("forecastJson") ?: ""
                        val forecast = Gson().fromJson(forecastJson, ForecastItem::class.java)

                        ForecastDetailScreen(
                            city = city,
                            forecast = forecast,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}