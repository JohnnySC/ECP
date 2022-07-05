package com.github.johnnysc.ecp.data.weather

interface GetWeatherInCity {

    fun getWeather(city: String): String
}