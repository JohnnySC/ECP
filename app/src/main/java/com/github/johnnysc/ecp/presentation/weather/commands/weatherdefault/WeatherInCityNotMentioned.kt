package com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault

import com.github.johnnysc.ecp.domain.weather.WeatherDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

object WeatherInCityNotMentioned : IsEmptyHandleUseCase<WeatherDefaultCityUseCase> {

    override suspend fun handle(useCase: WeatherDefaultCityUseCase): MessageUI = useCase.getWeather()
}