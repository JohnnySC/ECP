package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.presentation.commands.CommandHandler
import com.github.johnnysc.ecp.domain.weather.GetWeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

object WeatherInCityNotMentioned : CommandHandler<GetWeatherInCityUseCase>() {
    override suspend fun handle(useCase: GetWeatherInCityUseCase): MessageUI =
        MessageUI.Ai(useCase.getWeather())
}