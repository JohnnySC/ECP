package com.github.johnnysc.ecp.schedule.presentation.commands.deleteevent

import com.github.johnnysc.ecp.schedule.domain.DeleteEventUseCase
import com.github.johnnysc.ecp.schedule.domain.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class DeleteEventCommand(parser: ParseDeleteEvent) :
    Command.Abstract<ScheduleInteractor, DeleteEventUseCase>(parser)