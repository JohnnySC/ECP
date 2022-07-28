package com.github.johnnysc.ecp.presentation.weather.commands.setdefault

import com.github.johnnysc.ecp.domain.weather.DefaultCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherSetCityCommand(
    private val parser: ParseCity
) : Command<WeatherInteractor> {

    private var handler: HandleUseCase<DefaultCityUseCase>? = null

    override suspend fun handle(useCase: WeatherInteractor): MessageUI = handler!!.handle(useCase)

    override suspend fun canHandle(message: String): Boolean {
        handler = parser.map(message)
        return handler != null
    }
}