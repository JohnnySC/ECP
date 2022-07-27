package com.github.johnnysc.ecp.presentation.weather.commands.weatherincity

import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class WeatherInCityCommand(
    parser: ParseWeatherInCity
) : Command.Abstract<WeatherInteractor, WeatherInCityUseCase>(parser)