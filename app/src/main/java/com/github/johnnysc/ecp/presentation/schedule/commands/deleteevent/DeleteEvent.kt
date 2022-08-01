package com.github.johnnysc.ecp.presentation.schedule.commands.deleteevent

import com.github.johnnysc.ecp.domain.schedule.DeleteEventUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

data class DeleteEvent(private val name: String, private val date: String) : IsEmptyHandleUseCase<DeleteEventUseCase> {

    override suspend fun handle(useCase: DeleteEventUseCase): MessageUI = useCase.deleteEvent(name, date)
}
