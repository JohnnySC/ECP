package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.presentation.commands.CommandHandler
import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInCityCommand(
    private val parser: WeatherParser
) : Command<WeatherInteractor> {

    private var handler: CommandHandler<WeatherInCityUseCase>? = null

    override suspend fun handle(useCase: WeatherInteractor): MessageUI {
        return handler!!.handle(useCase)
    }

    override fun canHandle(message: String): Boolean {
        handler = parser.map(message)
        return if (handler == null) {
            handler != null
        } else true
    }
}