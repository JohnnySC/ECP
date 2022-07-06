package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.presentation.commands.CommandHandler
import com.github.johnnysc.ecp.domain.weather.GetWeatherInCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInCityCommand(
    private val parsers: WeatherParser
) : Command<WeatherInteractor> {

    private var domainObject: CommandHandler<GetWeatherInCityUseCase>? = null

    override suspend fun handle(useCase: WeatherInteractor): MessageUI {
        return domainObject!!.handle(useCase)
    }

    override fun canHandle(message: String): Boolean {
        domainObject = parser.map(message)
        return if (domainObject == null) {
            //domainObject = secondparser.map(message)
            domainObject != null
        } else true
    }
}