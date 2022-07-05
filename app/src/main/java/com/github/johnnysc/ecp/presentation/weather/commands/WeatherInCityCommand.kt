package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.GetWeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInCityCommand(manageResources: ManageResources) : Command<GetWeatherInCityUseCase> {

    private val triggers = manageResources.string(R.string.weather_triggers).split(",")

    override fun handle(useCase: GetWeatherInCityUseCase): MessageUI {

    }

    override fun canHandle(message: String): Boolean {
        var canHandle = false
        message.split(" ").find {
            triggers.contains(it)
        }?.let { canHandle = true }
        return canHandle
    }
}