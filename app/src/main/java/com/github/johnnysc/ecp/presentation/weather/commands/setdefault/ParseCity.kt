package com.github.johnnysc.ecp.presentation.weather.commands.setdefault

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.DefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser

class ParseCity(private val manageResources: ManageResources) : Parser<DefaultCityUseCase> {

    override fun map(data: String): HandleUseCase<DefaultCityUseCase>? {
        if (data.startsWith(manageResources.string(R.string.set_weather_command_start), true)) {
            val city = data.substring(R.string.set_weather_command_start).trim()
            if (city.isNotEmpty()) {
                return SetDefaultCity(manageResources, city)
            }
        }
        return null
    }
}