package com.github.johnnysc.ecp.presentation.weather.commands.weatherincity

import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.commands.IsEmptyUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

data class WeatherInCity(private val city: String) : IsEmptyUseCase<WeatherInCityUseCase> {

    override suspend fun handle(useCase: WeatherInCityUseCase): MessageUI = useCase.getWeather(city)
}


