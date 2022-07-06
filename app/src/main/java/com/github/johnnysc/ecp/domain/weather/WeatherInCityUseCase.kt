package com.github.johnnysc.ecp.domain.weather

interface WeatherInCityUseCase {

    suspend fun getWeather(city: String): String

    suspend fun getWeather(): String
}