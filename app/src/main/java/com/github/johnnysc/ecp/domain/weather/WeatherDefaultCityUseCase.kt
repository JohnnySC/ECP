package com.github.johnnysc.ecp.domain.weather

interface WeatherDefaultCityUseCase {

    suspend fun getWeather(): String
}