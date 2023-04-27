/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.mikkyboy.spizer.presentation

import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.wear.compose.material.*
import com.mikkyboy.spizer.R
import com.mikkyboy.spizer.presentation.data.remote.WeatherService
//import com.mikkyboy.spizer.presentation.data.remote.dto.WeatherResponse
import com.mikkyboy.spizer.presentation.theme.SpizerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    private val service = WeatherService.create()

    val CITY: String = "cotonou"
    val API: String = "417e4395bb9fa381afd1a45ad65d9ca8"

    private fun hasGps(): Boolean =
        packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasGps()) {
            println("This hardware doesn't have GPS.")
            // Fall back to functionality that doesn't use location or
            // warn the user that location function isn't available.
        } else {
            println("LocationGPS => ${PackageManager.FEATURE_LOCATION_GPS}");
        }

        var isSplashScreen = mutableStateOf(true)
        lifecycleScope.launch(Dispatchers.Default) {
            delay(3000)
            isSplashScreen.value = false
        }
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                isSplashScreen.value
            }
        }

//        weatherTask().execute()

        println("MyStatus => ${weatherTask().status}")

        setContent {
//            val weatherData = produceState<WeatherResponse>(
//                initialValue = WeatherResponse(
//                    base = "",
//                    id = 0,
//                    cod = 0,
//                    name = "",
//                    timeZone = 0,
//                ),
//                producer = {
//                    value = service.getWeather() ?: WeatherResponse(
//                        base = "",
//                        id = 0,
//                        cod = 0,
//                        name = "",
//                        timeZone = 0,
//                    )
//                }
//            )

            WearApp("Android")
        }

    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
//            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
//            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
//            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = result?.let { JSONObject(it) }
                val main = jsonObj?.getJSONObject("main")
                val sys = jsonObj?.getJSONObject("sys")
                val wind = jsonObj?.getJSONObject("wind")
                val weather = jsonObj?.getJSONArray("weather")?.getJSONObject(0)
                WeatherClass().weather = jsonObj?.getJSONArray("weather")?.getJSONObject(0)
                println("weather => $weather")

                val updatedAt: Long = jsonObj!!.getLong("dt")
                val updatedAtText =
                    "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main!!.getString("temp") + "°C"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise: Long = sys!!.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind!!.getString("speed")
                val weatherDescription = weather!!.getString("description")

                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                /* Populating extracted data into our views */
//                findViewById<TextView>(R.id.address).text = address
//                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
//                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
//                findViewById<TextView>(R.id.temp).text = temp
//                findViewById<TextView>(R.id.temp_min).text = tempMin
//                findViewById<TextView>(R.id.temp_max).text = tempMax
//                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
//                    Date(sunrise*1000)
//                )
//                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
//                    Date(sunset*1000)
//                )
//                findViewById<TextView>(R.id.wind).text = windSpeed
//                findViewById<TextView>(R.id.pressure).text = pressure
//                findViewById<TextView>(R.id.humidity).text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
//                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
            } catch (e: Exception) {
                print("=====ERR=====> $e")
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
//                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }
        }
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun WearApp(greetingName: String) {
    SpizerTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
//        HomeScreen(greetingName = greetingName)
        Scaffold(
//            timeText = { TimeText()},
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        ) {
            Navigation()
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.commot, greetingName)
    )
}

//@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
//@Composable
//fun DefaultPreview() {
//    WearApp("Preview Android")
//}