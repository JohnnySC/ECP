package com.github.johnnysc.ecp.schedule.presentation.commands.myday

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.schedule.domain.MyDayUseCase
import com.github.johnnysc.ecp.presentation.commands.Parser
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

class ParseMyDay(manageResources: ManageResources) : Parser<MyDayUseCase> {

    private val command = manageResources.string(R.string.my_day_command)

    override fun map(data: String): IsEmptyHandleUseCase<MyDayUseCase> {
        if (command.equals(data, true))
            return MyDay()
        return IsEmptyHandleUseCase.Empty()
    }
}