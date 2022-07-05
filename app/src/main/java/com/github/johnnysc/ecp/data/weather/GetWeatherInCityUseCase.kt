package com.github.johnnysc.ecp.data.weather

interface GetWeatherInCityUseCase {

    fun getWeather(city: String): String
}