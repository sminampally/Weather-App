package com.codingexercise.weatherapp.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codingexercise.weatherapp.data.remote.ForecastItem
import com.codingexercise.weatherapp.presentation.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastListScreen(
    city: String,
    forecastList: List<ForecastItem>,
    onItemClick: (ForecastItem) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        containerColor = Color.Gray.copy(0.1f),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults
                    .topAppBarColors(titleContentColor = Color.White, containerColor = Purple40),
                title = { Text(city) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            items(forecastList) { forecast ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(forecast) }
                        .padding(30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = forecast.weather.firstOrNull()?.main ?: "")
                    Text(text = "Temp: ${forecast.main.temp.toInt()}")
                }
                HorizontalDivider()
            }
        }
    }
}
