package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInDefaultCityCommand(private val manageResources: ManageResources) : Command<WeatherInteractor> {

    private val triggers = manageResources.string(R.string.weather_triggers).split(",")

    override fun canHandle(message: String): Boolean {
        var canHandle = false
        message.split(" ").forEach {
            if (triggers.contains(it))
                canHandle = true
        }
        return canHandle
    }

    override fun handle(useCase: WeatherInteractor): MessageUI {
        return if (useCase.defaultCitySet()) {
            MessageUI.Ai(
                String.format(
                    manageResources.string(R.string.weather_response),
                    useCase.weatherInDefaultCity()
                )
            )
        } else {
            MessageUI.AiError(manageResources.string(R.string.weather_no_default_city))
        }
    }
}