package com.codingexercise.weatherapp.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(onLookup: (String) -> Unit, error: String) {
    var city by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(0.1f))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = {
                city = it
                if (showError && it.isNotBlank()) {
                    showError = false
                }
            },
            label = { Text("City Name") },
            modifier = Modifier.fillMaxWidth()
        )
        if (showError) {
            Text("Please enter a city name", color = Color.Red)
        }
        if (error.isNotBlank()) {
            Text(error, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }
        Spacer(Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.Gray.copy(alpha = 0.1f)
            ),
            onClick = {
                if (city.isNotBlank()) {
                    onLookup(city)
                } else {
                    showError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
                .align(Alignment.CenterHorizontally)
                .border(width = 1.dp, color = Color.Gray, shape = RectangleShape),
            shape = RectangleShape
        ) {
            Text("Lookup")
        }
    }
}
