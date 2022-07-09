package com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.WeatherDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser

class ParseDefaultWeather(private val manageResources: ManageResources) : Parser<WeatherDefaultCityUseCase> {

    override fun map(data: String): HandleUseCase<WeatherDefaultCityUseCase>? {
        if (manageResources.string(R.string.what_is_weather_like).equals(
                data,
                true
            ) || manageResources.string(R.string.whats_weather_like).equals(
                data,
                true
            )
        ) {
            return WeatherInCityNotMentioned
        }
        return null
    }
}