package com.github.johnnysc.ecp.schedule.presentation.commands.myday

import com.github.johnnysc.ecp.schedule.domain.MyDayUseCase
import com.github.johnnysc.ecp.schedule.domain.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class MyDayCommand(parser: ParseMyDay) :
    Command.Abstract<ScheduleInteractor, MyDayUseCase>(parser)