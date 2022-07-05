package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.domain.weather.GetWeatherInCityUseCase

class WeatherInCityNotMentioned(private val getWeatherInCityUseCase: GetWeatherInCityUseCase) {

    suspend fun getWeather() = getWeatherInCityUseCase.getWeather()
}