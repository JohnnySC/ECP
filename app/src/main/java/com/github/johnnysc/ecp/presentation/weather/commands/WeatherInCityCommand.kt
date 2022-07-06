package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.GetWeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInCityCommand(private val manageResources: ManageResources) : Command.Abstract<GetWeatherInCityUseCase>() {

    override val triggers = manageResources.string(R.string.weather_triggers).split(",")

    override val excluded = manageResources.string(R.string.weather_excluded).split(",")

    override val ignored = manageResources.string(R.string.weather_ignored).split(",")

    override suspend fun handle(useCase: GetWeatherInCityUseCase): MessageUI {
        val result = when (data.size) {
            1 -> MessageUI.Ai(
                String().format(
                    manageResources.string(R.string.weather_response),
                    WeatherInCityNotMentioned(useCase).getWeather()
                )
            )
            2 -> MessageUI.Ai(
                String().format(
                    manageResources.string(R.string.weather_response),
                    WeatherInCityNow(useCase).getWeatherInCity(data[1])
                )
            )
            else -> MessageUI.AiError(manageResources.string(R.string.unexpected_error_message))
        }
        data.clear()
        return result
    }
}