package com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault

import com.github.johnnysc.ecp.domain.weather.WeatherDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

object WeatherInCityNotMentioned : HandleUseCase<WeatherDefaultCityUseCase> {

    override suspend fun handle(useCase: WeatherDefaultCityUseCase): MessageUI =
        MessageUI.Ai(useCase.getWeather())
}