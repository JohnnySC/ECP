package com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.WeatherDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser

class ParseDefaultWeather(private val manageResources: ManageResources) : Parser<WeatherDefaultCityUseCase> {

    override fun map(data: String): HandleUseCase<WeatherDefaultCityUseCase>? {
        if (data.equals(
                manageResources.string(R.string.what_is_weather_like),
                true
            ) || data.equals(
                manageResources.string(R.string.whats_weather_like),
                true
            )
        ) {
            return WeatherInCityNotMentioned()
        }
        return null
    }
}