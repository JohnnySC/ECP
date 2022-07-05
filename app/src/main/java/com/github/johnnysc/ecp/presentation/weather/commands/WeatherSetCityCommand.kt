package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.data.weather.SetDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherSetCityCommand : Command<SetDefaultCityUseCase> {

    override fun handle(useCase: SetDefaultCityUseCase): MessageUI {
        TODO("Not yet implemented")
    }

    override fun canHandle(message: String): Boolean {
        TODO("Not yet implemented")
    }
}