package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.GetWeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherInCityCommand(private val manageResources: ManageResources) : Command<GetWeatherInCityUseCase> {

    private val triggers = manageResources.string(R.string.weather_triggers).split(",")

    private val excluded = manageResources.string(R.string.weather_excluded).split(",")

    private val ignored = manageResources.string(R.string.weather_ignored).split(",")

    private val data = mutableListOf<String>()

    private fun parseData(data: String) {
        val cleanedData = data.apply {
            ignored.forEach { ignoredSymbols ->
                this.replace(ignoredSymbols, "")
            }
        }
        val dataList = cleanedData.split(" ").toMutableList()
        excluded.forEach {
            dataList.remove(it)
        }
        this.data.addAll(dataList)
    }

    override suspend fun handle(useCase: GetWeatherInCityUseCase): MessageUI {
        return when (data.size) {
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
    }

    override fun canHandle(message: String): Boolean {
        var canHandle = false
        message.split(" ").find {
            triggers.contains(it)
        }?.let {
            canHandle = true
            parseData(message)
        }
        return canHandle
    }
}