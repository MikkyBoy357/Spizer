package com.mikkyboy.spizer.presentation.nav_bar_screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.mikkyboy.spizer.R
import com.mikkyboy.spizer.presentation.data.remote.dto.MyWeatherResponse
import com.mikkyboy.spizer.presentation.data.remote.WeatherService
import com.mikkyboy.spizer.presentation.theme.MyBlue
import com.mikkyboy.spizer.presentation.theme.Red400

@Preview
@Composable
fun SettingScreen() {
    val service = WeatherService.create()

    val weatherData = produceState<MyWeatherResponse?>(
        initialValue = null,
        producer = {
            value = service.getWeather()
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = weatherData.value?.name ?: "City")
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
                contentDescription = "Weather",
                tint = Red400
            )
            Text(text = if (weatherData.value?.main?.temp != null) "${weatherData.value?.main?.temp}ºC" else "Temp", fontSize = 20.sp)
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                contentDescription = "Weather",
                tint = MyBlue
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
//        Text(text = "descr: ${weatherData.value}")
        Text(text = weatherData.value?.weather?.get(0)?.description ?: "Description")
    }
}