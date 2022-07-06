package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.presentation.commands.CommandHandler
import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInCity(private val city: String) : CommandHandler<WeatherInCityUseCase>() {
    override suspend fun handle(useCase: WeatherInCityUseCase): MessageUI =
        MessageUI.Ai(useCase.getWeather(city))
}