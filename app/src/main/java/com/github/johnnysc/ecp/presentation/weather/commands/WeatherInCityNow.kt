package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.domain.weather.GetWeatherInCityUseCase

class WeatherInCityNow(private val getWeatherInCityUseCase: GetWeatherInCityUseCase) {

    suspend fun getWeatherInCity(city: String): String = getWeatherInCityUseCase.getWeather(city)
}