package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.domain.weather.DefaultCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.commands.CommandHandler
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherSetCityCommand(
    private val parser: CityParser
) : Command<WeatherInteractor> {

    private var handler: CommandHandler<DefaultCityUseCase>? = null

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