package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.GetWeatherInCityUseCase
import com.github.johnnysc.ecp.domain.weather.SetDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherSetCityCommand(private val manageResources: ManageResources) : Command.Abstract<SetDefaultCityUseCase>() {

    override val triggers: List<String> = manageResources.string(R.string.set_default_city_triggers).split(",")

    override val excluded: List<String> = manageResources.string(R.string.set_default_city_excluded).split(",")

    override val ignored: List<String> = manageResources.string(R.string.set_default_city_ignored).split(",")

    override suspend fun handle(useCase: SetDefaultCityUseCase): MessageUI {
        val result = when (data.size) {
            2 -> MessageUI.Ai(
                String().format(
                    manageResources.string(R.string.weather_response),
                    SetDefaultCity(useCase).setCity(data[1])
                )
            )
            else -> MessageUI.AiError(manageResources.string(R.string.unexpected_error_message))
        }
        data.clear()
        return result
    }
}