package com.github.johnnysc.ecp.presentation.schedule

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.schedule.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.AbstractFeatureChain
import com.github.johnnysc.ecp.presentation.schedule.commands.addevent.ParseAddEvent
import com.github.johnnysc.ecp.presentation.schedule.commands.addevent.AddEventCommand
import com.github.johnnysc.ecp.presentation.schedule.commands.deleteevent.ParseDeleteEvent
import com.github.johnnysc.ecp.presentation.schedule.commands.deleteevent.DeleteEventCommand
import com.github.johnnysc.ecp.presentation.schedule.commands.myday.ParseMyDay
import com.github.johnnysc.ecp.presentation.schedule.commands.myday.MyDayCommand
import com.github.johnnysc.ecp.presentation.schedule.commands.myschedule.ParseMySchedule
import com.github.johnnysc.ecp.presentation.schedule.commands.myschedule.MyScheduleCommand

class ScheduleChain(
    interactor: ScheduleInteractor,
    private val manageResources: ManageResources,
    commands: List<Command<ScheduleInteractor>> = listOf(
        MyScheduleCommand(ParseMySchedule(manageResources)),
        MyDayCommand(ParseMyDay(manageResources)),
        AddEventCommand(ParseAddEvent(manageResources)),
        DeleteEventCommand(ParseDeleteEvent(manageResources))
    )
) : AbstractFeatureChain<ScheduleInteractor>(interactor, commands)