package com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault

import com.github.johnnysc.ecp.domain.weather.WeatherDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.IsEmptyUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

object WeatherInCityNotMentioned : IsEmptyUseCase<WeatherDefaultCityUseCase> {

    override suspend fun handle(useCase: WeatherDefaultCityUseCase): MessageUI = useCase.getWeather()
}