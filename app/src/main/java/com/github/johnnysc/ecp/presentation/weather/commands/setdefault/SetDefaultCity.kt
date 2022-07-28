package com.github.johnnysc.ecp.presentation.weather.commands.setdefault

import com.github.johnnysc.ecp.domain.weather.DefaultCityUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

data class SetDefaultCity(private val city: String) : IsEmptyHandleUseCase<DefaultCityUseCase> {

    override suspend fun handle(useCase: DefaultCityUseCase): MessageUI = useCase.setDefault(city)
}