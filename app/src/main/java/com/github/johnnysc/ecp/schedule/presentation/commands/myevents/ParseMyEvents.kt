package com.github.johnnysc.ecp.schedule.presentation.commands.myevents

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.schedule.domain.MyEventsUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

class ParseMyEvents(manageResources: ManageResources) : Parser<MyEventsUseCase> {

    private val command = manageResources.string(R.string.my_events_command)

    override fun map(data: String): IsEmptyHandleUseCase<MyEventsUseCase> {
        if (command.equals(data, true))
            return MyEvents()
        return IsEmptyHandleUseCase.Empty()
    }
}