package com.github.johnnysc.ecp.domain.weather

interface GetWeatherInCityUseCase {

    suspend fun getWeather(city: String): String

    suspend fun getWeather(): String
}