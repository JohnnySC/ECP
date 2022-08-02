package com.github.johnnysc.ecp.presentation.schedule.commands.deleteevent

import com.github.johnnysc.ecp.domain.schedule.DeleteEventUseCase
import com.github.johnnysc.ecp.domain.schedule.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class DeleteEventCommand(parser: ParseDeleteEvent) :
    Command.Abstract<ScheduleInteractor, DeleteEventUseCase>(parser)