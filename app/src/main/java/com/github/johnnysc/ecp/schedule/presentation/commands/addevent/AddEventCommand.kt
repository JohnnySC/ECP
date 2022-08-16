package com.github.johnnysc.ecp.schedule.presentation.commands.addevent

import com.github.johnnysc.ecp.schedule.domain.AddEventUseCase
import com.github.johnnysc.ecp.schedule.domain.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class AddEventCommand(parser: ParseAddEvent) :
    Command.Abstract<ScheduleInteractor, AddEventUseCase>(parser)