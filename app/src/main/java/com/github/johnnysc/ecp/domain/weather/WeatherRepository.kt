package com.github.johnnysc.ecp.domain.weather

interface WeatherRepository {

    suspend fun getWeatherInCity(city: String): WeatherDomain

    suspend fun getWeatherInDefaultCity(): WeatherDomain

    suspend fun saveDefaultCity(newCity: String)
}