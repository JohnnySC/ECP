package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.domain.weather.SetDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherSetCityCommand() : Command<SetDefaultCityUseCase> {

    private var message: String? = null

    override fun handle(useCase: SetDefaultCityUseCase): MessageUI {

    }

    override fun canHandle(message: String): Boolean {
        this.message = message
    }
}