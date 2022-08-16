package com.github.johnnysc.ecp.schedule.presentation.commands.myevents

import com.github.johnnysc.ecp.schedule.domain.MyEventsUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

class MyEvents : IsEmptyHandleUseCase<MyEventsUseCase> {

    override suspend fun handle(useCase: MyEventsUseCase): MessageUI = useCase.myEvents()
}
