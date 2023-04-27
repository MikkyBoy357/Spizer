package com.mikkyboy.spizer.presentation.data.remote

import com.mikkyboy.spizer.presentation.data.remote.dto.MyWeatherResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class WeatherServiceImpl(
    private val client: HttpClient
) : WeatherService {
    override suspend fun getWeather(): MyWeatherResponse? {
        return try {
            client.get {
                url(HttpRoutes.WEATHER)
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
//            WeatherResponse(
//                base = "",
//                id = 0,
//                cod = 0,
//                name = "",
//                timeZone = 0,
//            )
            null
        }
    }
}