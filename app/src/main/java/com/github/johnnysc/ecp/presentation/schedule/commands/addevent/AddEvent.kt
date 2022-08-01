package com.github.johnnysc.ecp.presentation.schedule.commands.addevent

import com.github.johnnysc.ecp.domain.schedule.AddEventUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

data class AddEvent(private val name: String, private val date: String) : IsEmptyHandleUseCase<AddEventUseCase> {

    override suspend fun handle(useCase: AddEventUseCase): MessageUI = useCase.addEvent(name, date)
}
