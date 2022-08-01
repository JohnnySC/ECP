package com.github.johnnysc.ecp.presentation.schedule.commands.addevent

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.schedule.AddEventUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

class ParseAddEvent(manageResources: ManageResources) : Parser<AddEventUseCase> {

    private val commandStart = manageResources.string(R.string.add_event_command_start)

    override fun map(data: String): IsEmptyHandleUseCase<AddEventUseCase> {
        if (data.startsWith(commandStart, true)) {
            val words = data.substring(commandStart.length).split(" on")
            val name = words.first().trim()
            val date = words.last().trim()
            if (name.isNotEmpty() && date.isNotEmpty() && date != name)
                return AddEvent(name, date)
        }
        return IsEmptyHandleUseCase.Empty()
    }
}