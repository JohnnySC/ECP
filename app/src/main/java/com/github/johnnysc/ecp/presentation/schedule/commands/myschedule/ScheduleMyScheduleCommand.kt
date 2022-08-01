package com.github.johnnysc.ecp.presentation.schedule.commands.myschedule

import com.github.johnnysc.ecp.domain.schedule.MyScheduleUseCase
import com.github.johnnysc.ecp.domain.schedule.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class ScheduleMyScheduleCommand(parser: ParseMySchedule) :
    Command.Abstract<ScheduleInteractor, MyScheduleUseCase>(parser)