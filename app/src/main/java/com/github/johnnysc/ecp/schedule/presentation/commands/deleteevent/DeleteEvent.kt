package com.github.johnnysc.ecp.schedule.presentation.commands.deleteevent

import com.github.johnnysc.ecp.schedule.domain.DeleteEventUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

data class DeleteEvent(private val name: String, private val date: Long) : IsEmptyHandleUseCase<DeleteEventUseCase> {

    override suspend fun handle(useCase: DeleteEventUseCase): MessageUI = useCase.deleteEvent(name, date)
}