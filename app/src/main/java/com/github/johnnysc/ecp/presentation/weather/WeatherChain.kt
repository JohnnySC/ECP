package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.AbstractFeatureChain
import com.github.johnnysc.ecp.presentation.weather.commands.setdefault.ParseCity
import com.github.johnnysc.ecp.presentation.weather.commands.setdefault.WeatherSetCityCommand
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.ParseDefaultWeather
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.WeatherDefaultCommand
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.ParseWeatherInCity
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.WeatherInCityCommand

class WeatherChain(
    interactor: WeatherInteractor,
    private val manageResources: ManageResources,
    commands: List<Command<WeatherInteractor>>
    = listOf(
        WeatherInCityCommand(ParseWeatherInCity(manageResources)),
        WeatherDefaultCommand(ParseDefaultWeather(manageResources)),
        WeatherSetCityCommand(ParseCity(manageResources))
    )
) : AbstractFeatureChain<WeatherInteractor>(interactor, commands)