package com.codingexercise.weatherapp.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codingexercise.weatherapp.data.remote.ForecastItem
import com.codingexercise.weatherapp.presentation.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastDetailScreen(
    city: String,
    forecast: ForecastItem,
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
                            tint = Color.White,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(20.dp))
            Text(
                fontWeight = FontWeight.SemiBold,
                text = "${forecast.main.temp.toInt()}",
                fontSize = 60.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                fontSize = 22.sp,
                text = "Feels Like: ${forecast.main.feels_like.toInt()}",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(Alignment.End)
            )

            Spacer(Modifier.height(26.dp))

            Text(
                text = forecast.weather.firstOrNull()?.main ?: "",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(20.dp))

            Text(
                text = forecast.weather.firstOrNull()?.description ?: "",
                fontSize = 22.sp,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}
