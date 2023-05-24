package com.mikkyboy.spizer.presentation.nav_bar_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.mikkyboy.spizer.presentation.widgets.WeatherItem

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
        if (weatherData.value == null) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "Loading...")
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (weatherData.value?.main?.temp != null && weatherData.value?.main?.temp!! >= 20) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_wb_sunny_24),
                        contentDescription = "Weather",
                        tint = Color.Yellow
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_cloud_24),
                        contentDescription = "Weather",
                        tint = Color.Yellow
                    )
                }
                Text(text = weatherData.value?.name ?: "City")
                Icon(
                    painter = painterResource(id = R.drawable.baseline_wb_sunny_24),
                    contentDescription = "Weather",
                    tint = Color.Transparent
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
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
                Text(
                    text = if (weatherData.value?.main?.temp != null) "${weatherData.value?.main?.temp}ºC" else "Temp",
                    fontSize = 20.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                    contentDescription = "Weather",
                    tint = MyBlue
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "MinTemp: ${weatherData.value?.main?.tempMin}",
                    fontSize = 10.sp
                )
                Text(
                    text = "MaxTemp: ${weatherData.value?.main?.tempMax}",
                    fontSize = 10.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
//        Text(text = "descr: ${weatherData.value}")
            Text(
                text = weatherData.value?.weather?.get(0)?.description ?: "Description",
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherItem(
                    modifier = Modifier,
                    title = "Wind",
                    value = weatherData.value?.wind?.speed.toString(),
                    icon = R.drawable.wind,
                )
                WeatherItem(
                    modifier = Modifier,
                    title = "Humidity",
                    value = weatherData.value?.main?.humidity.toString(),
                    icon = R.drawable.humidity,
                )
                WeatherItem(
                    modifier = Modifier,
                    title = "Pressure",
                    value = weatherData.value?.main?.pressure.toString(),
                    icon = R.drawable.pressure,
                )
            }
        }
    }
}