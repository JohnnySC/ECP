package com.github.johnnysc.ecp.schedule.presentation.commands.addevent

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.schedule.domain.AddEventUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ParseAddEvent(manageResources: ManageResources) : Parser<AddEventUseCase> {

    private val commandStart = manageResources.string(R.string.add_event_command_start)

    private val today = manageResources.string(R.string.today)

    override fun map(data: String): IsEmptyHandleUseCase<AddEventUseCase> {
        if (data.startsWith(commandStart, true)) {
            val words = data.substring(commandStart.length).split(" on")
            val name = words.first().trim()
            val date = words.last().trim()
            if (name.isNotEmpty() && date.isNotEmpty() && date != name) {
                val definedDate =
                    if (today == date) System.currentTimeMillis() else try {
                        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(date)?.time
                    } catch (e: ParseException) {
                        0L
                    } ?: 0L
                return AddEvent(name, definedDate)
            }
        }
        return IsEmptyHandleUseCase.Empty()
    }
}