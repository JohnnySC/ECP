package com.github.johnnysc.ecp.presentation.weather.commands.setdefault

import com.github.johnnysc.ecp.domain.weather.DefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

data class SetDefaultCity(private val city: String) : HandleUseCase<DefaultCityUseCase> {

    override suspend fun handle(useCase: DefaultCityUseCase): MessageUI = useCase.setDefault(city)
}