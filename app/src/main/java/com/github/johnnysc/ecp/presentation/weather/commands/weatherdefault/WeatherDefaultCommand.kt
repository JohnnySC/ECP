package com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault

import com.github.johnnysc.ecp.domain.weather.WeatherDefaultCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class WeatherDefaultCommand(parser: ParseDefaultWeather) :
    Command.Abstract<WeatherInteractor, WeatherDefaultCityUseCase>(parser)