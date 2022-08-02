package com.github.johnnysc.ecp.presentation.schedule.commands.myday

import com.github.johnnysc.ecp.domain.schedule.MyDayUseCase
import com.github.johnnysc.ecp.domain.schedule.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

class MyDayCommand(parser: ParseMyDay) :
    Command.Abstract<ScheduleInteractor, MyDayUseCase>(parser)