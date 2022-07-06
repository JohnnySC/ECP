package com.github.johnnysc.ecp.presentation.weather.commands.setdefault

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.DefaultCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class SetDefaultCity(
    private val manageResources: ManageResources,
    private val city: String
) : HandleUseCase<DefaultCityUseCase> {

    override suspend fun handle(useCase: DefaultCityUseCase): MessageUI {
        useCase.setDefault(city)
        return MessageUI.Ai(manageResources.string(R.string.set_weather_command_success))
    }
}