package com.mikkyboy.spizer.presentation.data.remote

object HttpRoutes {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    const val WEATHER = "$BASE_URL/weather?q=cotonou&units=metric&appid=417e4395bb9fa381afd1a45ad65d9ca8"

//    private const val BASE_URL = "https://jsonplaceholder.typicode.com"
//    const val WEATHER = "$BASE_URL/posts"
}