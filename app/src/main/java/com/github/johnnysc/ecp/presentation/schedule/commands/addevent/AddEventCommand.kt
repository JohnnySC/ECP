package com.github.johnnysc.ecp.presentation.schedule.commands.addevent

import com.github.johnnysc.ecp.domain.schedule.AddEventUseCase
import com.github.johnnysc.ecp.domain.schedule.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class AddEventCommand(parser: ParseAddEvent) :
    Command.Abstract<ScheduleInteractor, AddEventUseCase>(parser)