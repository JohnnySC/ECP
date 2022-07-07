package com.github.johnnysc.ecp.presentation.weather.commands.weatherincity

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser

class ParseWeatherInCity(private val manageResources: ManageResources) : Parser<WeatherInCityUseCase> {

    override fun map(data: String): HandleUseCase<WeatherInCityUseCase>? {
        val commandStart = manageResources.string(R.string.what_weather_command_start)
        if (data.startsWith(commandStart, true)) {
            val city = data.substring(commandStart.length).trim()
            if (city.isNotEmpty()) {
                return WeatherInCity(city)
            }
        }
        return null
    }
}