package com.mikkyboy.spizer.presentation.data.remote

//import com.mikkyboy.spizer.presentation.data.remote.dto.WeatherResponse
import com.mikkyboy.spizer.presentation.data.remote.dto.MyWeatherResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface WeatherService {

    suspend fun getWeather(): MyWeatherResponse?

    companion object {
        fun create(): WeatherService {
            return WeatherServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            json = kotlinx.serialization.json.Json {
                                isLenient = true
                                ignoreUnknownKeys = true
                                allowSpecialFloatingPointValues = true
                                useArrayPolymorphism = false
                            }
                        )
                    }
                }
            )
        }
    }
}