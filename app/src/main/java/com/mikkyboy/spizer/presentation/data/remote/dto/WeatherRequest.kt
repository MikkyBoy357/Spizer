package com.mikkyboy.spizer.presentation.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherRequest(
    val base: String,
    val name: String,
    val timezone: Int,
//    val id: Int,
    val cod: Int,

//    val base: String,
//    val name: String,
//    val timeZone: Int,
////    val cod: Int,
)
