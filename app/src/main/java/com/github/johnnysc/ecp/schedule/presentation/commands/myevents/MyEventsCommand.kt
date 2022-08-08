package com.github.johnnysc.ecp.schedule.presentation.commands.myevents

import com.github.johnnysc.ecp.schedule.domain.MyEventsUseCase
import com.github.johnnysc.ecp.schedule.domain.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class MyEventsCommand(parser: ParseMyEvents) :
    Command.Abstract<ScheduleInteractor, MyEventsUseCase>(parser)