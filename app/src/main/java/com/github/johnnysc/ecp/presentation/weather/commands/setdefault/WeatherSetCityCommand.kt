package com.github.johnnysc.ecp.presentation.weather.commands.setdefault

import com.github.johnnysc.ecp.domain.weather.DefaultCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class WeatherSetCityCommand(parser: ParseCity) :
    Command.Abstract<WeatherInteractor, DefaultCityUseCase>(parser)