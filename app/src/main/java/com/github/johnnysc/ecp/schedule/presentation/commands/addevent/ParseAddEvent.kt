package com.github.johnnysc.ecp.schedule.presentation.commands.addevent

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.schedule.domain.AddEventUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase
import com.github.johnnysc.ecp.schedule.presentation.commands.ConvertTime

class ParseAddEvent(
    manageResources: ManageResources,
    private val convertTime: ConvertTime
) : Parser<AddEventUseCase> {

    private val commandStart = manageResources.string(R.string.add_event_command_start)

    private val today = manageResources.string(R.string.today)

    override fun map(data: String): IsEmptyHandleUseCase<AddEventUseCase> {
        if (data.startsWith(commandStart, true)) {
            val words = data.substring(commandStart.length).split(" on")
            val name = words.first().trim()
            val date = words.last().trim()
            if (name.isNotEmpty() && date.isNotEmpty() && date != name) {
                val definedDate = if (today == date)
                    System.currentTimeMillis()
                else try {
                    convertTime.fromStringToTime(date)
                } catch (e: Exception) {
                    return IsEmptyHandleUseCase.Empty()
                }
                return AddEvent(name, definedDate)
            }
        }
        return IsEmptyHandleUseCase.Empty()
    }
}