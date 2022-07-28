package com.github.johnnysc.ecp.presentation.weather.commands.weatherincity

import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInCityCommand(
    private val parser: ParseWeatherInCity
) : Command<WeatherInteractor> {

    private var handler: HandleUseCase<WeatherInCityUseCase>? = null

    override suspend fun handle(useCase: WeatherInteractor): MessageUI = handler!!.handle(useCase)

    override suspend fun canHandle(message: String): Boolean {
        handler = parser.map(message)
        return handler != null
    }
}