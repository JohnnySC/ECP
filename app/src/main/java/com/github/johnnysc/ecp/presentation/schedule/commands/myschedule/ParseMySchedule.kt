package com.github.johnnysc.ecp.presentation.schedule.commands.myschedule

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.schedule.MyScheduleUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

class ParseMySchedule(manageResources: ManageResources) : Parser<MyScheduleUseCase> {

    private val command = manageResources.string(R.string.my_schedule_command)

    override fun map(data: String): IsEmptyHandleUseCase<MyScheduleUseCase> {
        if (command.equals(data, true))
            return MySchedule()
        return IsEmptyHandleUseCase.Empty()
    }
}