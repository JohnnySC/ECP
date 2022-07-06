package com.github.johnnysc.ecp.presentation.weather.commands.weatherincity

import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

data class WeatherInCity(private val city: String) : HandleUseCase<WeatherInCityUseCase> {

    override suspend fun handle(useCase: WeatherInCityUseCase): MessageUI =
        MessageUI.Ai(useCase.getWeather(city))
}