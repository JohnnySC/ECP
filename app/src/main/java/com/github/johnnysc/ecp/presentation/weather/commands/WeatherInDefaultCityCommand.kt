package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.GetWeatherInCity
import com.github.johnnysc.ecp.data.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInDefaultCityCommand(private val manageResources: ManageResources) : Command {

    private val triggers = manageResources.string(R.string.weather_triggers).split(",")

    override fun <GetWeatherInCity> handle(useCase: GetWeatherInCity): MessageUI {
        TODO()
    }

    override fun canHandle(message: String): Boolean {
        var canHandle = false
        message.split(" ").find {
            triggers.contains(it)
        }?.let { canHandle = true }
        return canHandle
    }
}