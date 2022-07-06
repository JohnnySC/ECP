package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.commands.CommandHandler
import com.github.johnnysc.ecp.presentation.commands.Parser

class WeatherParser(private val manageResources: ManageResources) : Parser<WeatherInCityUseCase> {
    override fun map(data: String): CommandHandler<WeatherInCityUseCase>? {
        return if (data.equals(
                manageResources.string(R.string.what_is_weather_like),
                true
            ) || data.equals(
                manageResources.string(R.string.whats_weather_like),
                true
            )
        ) {
            WeatherInCityNotMentioned
        } else if (data.startsWith(manageResources.string(R.string.what_weather_command_start), true)) {
            val city = data.substring(R.string.what_weather_command_start).trim()
            if (city.isNotEmpty()) {
                WeatherInCity(city)
            } else {
                null
            }
        } else {
            null
        }
    }
}